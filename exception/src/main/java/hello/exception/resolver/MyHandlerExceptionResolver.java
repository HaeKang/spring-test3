package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if (ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());    // 상태코드 400
                return new ModelAndView();  // 새로운 ModelAndView 반환, 이를 통해 정상적으로 동작됨. 예외를 삼켜버리게, 해결하게(resolver) 되는 것
            }

//            if (ex ...){
//                response.getWriter().println("HTTP 응답 바디에 직접 데이터 넣기 가능, 여기에 json 넣으면 api 응답 처리 가능");
//            }

        } catch (IOException e){
            log.error("resolver ex", e);
        }

        return null;
    }
}
