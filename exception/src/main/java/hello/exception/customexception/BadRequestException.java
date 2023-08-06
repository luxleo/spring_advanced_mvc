package hello.exception.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "요청을 잘못 하여 오류가 터졌소")
public class BadRequestException extends RuntimeException {

}
