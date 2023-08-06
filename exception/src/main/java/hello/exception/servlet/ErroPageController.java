package hello.exception.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Slf4j
@Controller
public class ErroPageController {
    // RequestDispatcher에 정의된 상수
    private static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
    private static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
    private static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
    private static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
    private static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
    private static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";
    @RequestMapping("/error-page/404")
    public String error404(HttpServletRequest req, HttpServletResponse res) {
        log.info("error page 404");
        printErrorInfo(req);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String error500(HttpServletRequest req, HttpServletResponse res) {
        log.info("error page 500");
        printErrorInfo(req);
        return "error-page/500";
    }

    private void printErrorInfo(HttpServletRequest req) {
        log.info("ERROR_EXCEPTION : {}",req.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE : {}",req.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE : {}",req.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI : {}",req.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME : {}",req.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE : {}",req.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatcherType = {}", req.getDispatcherType());

    }
    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> error500Api(
            HttpServletRequest req, HttpServletResponse response) {
        log.info("API 500 Error handler called");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) req.getAttribute(ERROR_EXCEPTION);

        result.put("status", req.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) req.getAttribute(ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));

    }
}
