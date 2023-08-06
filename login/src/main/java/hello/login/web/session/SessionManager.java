package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "my_session_id";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * session creation
     */
    public void createSession(Object value, HttpServletResponse response) {
        // session id 생성, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString(); // universal unique id
        sessionStore.put(sessionId, value);

        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);// cmd+option+c -> 상수로 만든다.
        response.addCookie(mySessionCookie);
    }
    /**
     * session search
     */
    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request, String CookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .findAny() // findFirst -> 순서대로 돌려서 첫번쨰, findAny -> 병렬적으로(조회) 하여 가장 먼저 나온놈
                .orElse(null);
    }
}
