package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    /*
        @RestControllerAdvice 를 통해 여러 컨트롤러에서 발생하는 오류들을 한번에 모아둠.
        대상을 지정 안하면 모든 컨트롤러에 대해 적용
        
        1. 특정 어노테이션 지정
        @RestControllerAdvice(annotations = RestController.class)
        이렇게 annotations을 해주면 해당 어노테이션이 있는 모든 컨트롤러에 대해 진행
        
        2. 특정 패키지 지정
        @ControllerAdvice("org.example.controllers")
        이렇게 패키지명을 지정해 줄 수도 있음
        
        3. 특정 class 지정
        @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
        이렇게 특정 class만 가능
        
        @RestControllerAdvice는 @ControllerAdvice와 같고 @ResponseBody가 추가된 것임.
        @Controller와 @RestController의 차이와 동일
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


}
