package hello.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Slf4j
@Controller
/**
 * HttpServlet의 예외처리 방식
 * 1, Exception ->
 * 2. response.sendError
 */
public class ServletExceptionController {
    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("런타임 예외 발생");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404에러 구먼유");
    }
    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400, "400에러 구먼유");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse res) throws IOException {
        res.sendError(500, "500 에러 구먼유, 서버가 처리를 못했슈");
    }


}
