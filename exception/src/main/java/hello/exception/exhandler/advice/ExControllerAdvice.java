package hello.exception.exhandler.advice;

import hello.exception.customexception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    /**
     * 이 컨트롤러 안에서 IllegalArgumentException은 내가 처리한다~~
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 이 녀석이 없다면 200으로 자동 지정한다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalArgExHandler(IllegalArgumentException exception) {
        log.error("[일리걸 아르그 핸들러요] ex",exception);
        return new ErrorResult("당신 요청 나빠", exception.getMessage());
    }

    /**
     * UserException은 이 컨트롤러에서 내가 처리한다
     * 그라고 위에(illegalArgsExHandler) 처럼 어노테이션에 .class 명시할 필요 없다
     * 파라미터만 에러와 일치한다면 ㅎㅎ
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[UserException(내가 만듬 ㅎㅎ)핸들러요] ex", e);
        ErrorResult errorResult = new ErrorResult("이름 단디 보소 코드", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult generalExHandler(Exception ex) {
        log.error("[Exception 잡는 대장 핸들러다] ex", ex);
        return new ErrorResult("Exception ㅋ", "큰 놈은 내가 다 잡지요 서버오류요");
    }



}
