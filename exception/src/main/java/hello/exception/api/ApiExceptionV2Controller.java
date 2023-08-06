package hello.exception.api;

import hello.exception.customexception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
// ControllerAdvice에 에러처리를 넘겨주었소 ㅎㅎ

//    /**
//     * 이 컨트롤러 안에서 IllegalArgumentException은 내가 처리한다~~
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST) // 이 녀석이 없다면 200으로 자동 지정한다.
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalArgExHandler(IllegalArgumentException exception) {
//        log.error("[일리걸 아르그 핸들러요] ex",exception);
//        return new ErrorResult("당신 요청 나빠", exception.getMessage());
//    }
//
//    /**
//     * UserException은 이 컨트롤러에서 내가 처리한다
//     * 그라고 위에(illegalArgsExHandler) 처럼 어노테이션에 .class 명시할 필요 없다
//     * 파라미터만 에러와 일치한다면 ㅎㅎ
//     */
//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
//        log.error("[UserException(내가 만듬 ㅎㅎ)핸들러요] ex", e);
//        ErrorResult errorResult = new ErrorResult("이름 단디 보소 코드", e.getMessage());
//        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
//
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorResult generalExHandler(Exception ex) {
//        log.error("[Exception 잡는 대장 핸들러다] ex", ex);
//        return new ErrorResult("Exception ㅋ", "큰 놈은 내가 다 잡지요 서버오류요");
//    }
//
//
    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자 구려");
        } else if (id.equals("bad")) {
            throw new IllegalArgumentException("입력값이 매우 잘못 되었구려? v2");
        } else if (id.equals("user-ex")) {
            throw new UserException("사용자 오류 났네?");
        }
        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;

    }
}
