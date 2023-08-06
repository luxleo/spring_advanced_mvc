package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    // interface에 default 키워드 들어간 메소드는 필히 오버라이드 할 필요 없다.
    private static final String[] whiteList = {"/", "/members/add", "/login","/logout","/css/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            log.info("인증 체크 필터 시작 = {}",requestURI);
            if (isLoginCheckPath(requestURI)) {
                // 세션 체크 시작
                HttpSession session = httpRequest.getSession(false);
                if(session==null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 접근 시도 = {}", requestURI);
                    // 로그인 성공시, 기존에 요청했던 URI로 다시 복귀 시켜주기 위한 로직
                    httpResponse.sendRedirect("/login?redirectURL="+requestURI);
                    return;
                }
            }
        chain.doFilter(request,response);
        }catch (Exception e){
            throw e; // 여기서 예외 로깅 가능하지만 톰캣까지 올려 주어야한다.
        }finally {
            log.info("인증 체크 필터 종료 = {}", requestURI);
        }
    }

    /**
     * 화이트리스트의 경우 인증체크 안
     */
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
