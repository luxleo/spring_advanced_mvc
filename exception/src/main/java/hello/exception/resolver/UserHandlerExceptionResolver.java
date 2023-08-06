package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.customexception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler
            , Exception ex) {
        try {
            if (ex instanceof UserException) {
                String acceptHeader = request.getHeader("accept");

                // 400에러로 처리 해보입시더 ㅎㅎ
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if ("application/json".equals(acceptHeader)) {
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());
                    // json -> string으로 바꿔가 이쁘게 담아 보냅시더
                    String serializedResult = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(serializedResult);
                    return new ModelAndView(); // 스무스하게 가자고, 보여주기 식이더라도
                }else{
                    // html로 요청한 사람들에게 해당하오
                    return new ModelAndView("error/500");
                }
            }
        } catch (IOException e) {
            log.info("resolver ex 보여주께요이",e);
        }
        return null;
    }
}
