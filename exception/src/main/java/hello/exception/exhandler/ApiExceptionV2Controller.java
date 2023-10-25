package hello.exception.exhandler;

import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    /*
     Spring의 우선순위는 항상 자세한 것이 우선됨.
     ex) 부모, 자식 둘다 호출 대상이 된다면 자식이 호출 대상이 됨.
    */

    /*
        1. IllegalArgumentException 케이스
        해당 컨트롤러에서(ApiExceptionV2Controller)
        IllegalArgumentException 발생 시 해당 로직 수행

        아래 로직을 수행하면 정상 흐름이 되어버리기때문에 상태 코드가 200이 됨.
        따라서 @ResponseStatus를 통해 400 을 반환하도록 한다.
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    /*
        2. UserException 케이스
        @ExceptionHandler(UserException.class) 이렇게 쓸 수 있지만,
        파라미터에 UserException e 라고 UserException을 언급해주면 어노테이션에서 저 문구를 생략해주어도 된다.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    /*
        3. 일반적인 Exception 케이스
        1번, 2번에서 처리하지 못하는 예외를 여기서 최종적으로 처리하게 됨.
        INTERNAL_SERVER_ERROR을 통해 500 error 발생
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
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
