package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    /**
     * 특정 타입의 Exception이 오면 정상적인 ModelView를 반환(오류를 해결)한다
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler
            , Exception ex) {
        log.info("자 ex 입장 했습니더~~");
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("허허~ IllegalArgumentException이구려 내 해결하겠소 - by HandlerExceptionResolver(a.k.a 해결사) ");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver로 들어온 ex", e);
        }
        log.info("ex 입장한거 해결 몬해쓰이 고마 던지겠습니더~~");
        return null;
    }
}
