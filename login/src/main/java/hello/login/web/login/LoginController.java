package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    //@PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail","로그인에 실패");
            return "login/loginForm";
        }

        // cookie에 시간 정보 주지 않으면 세션 쿠키
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getLoginId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }

    /**
     * session 이용
     */
    //@PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail","로그인에 실패");
            return "login/loginForm";
        }

        // 세션 관리자를 통해 세션 생성후, 회원 데이터 보관
        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }
    //@PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail","로그인에 실패");
            return "login/loginForm";
        }

        // 세션 관리자를 통해 세션 생성후, 회원 데이터 보관
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    /**
     * 미 로그인 사용자가, 로그인하였을때 기존에 요청한 URI로 리다이렉트
     */
    @PostMapping("/login")
    public String loginV4(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail","로그인에 실패");
            return "login/loginForm";
        }

        // 세션 관리자를 통해 세션 생성후, 회원 데이터 보관
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:" + redirectURL;
    }

    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest req){
        sessionManager.expire(req);
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest req){
        HttpSession session = req.getSession(false); // true 로 설정시 새로운 세션 생성한다.
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
