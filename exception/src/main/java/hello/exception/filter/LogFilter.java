package hello.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter 초기화");
    }

    @Override
    public void destroy() {
        log.info("log filter 종료");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter 시작");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}] [{}] [{}]", uuid, request.getDispatcherType(), requestURI);
            chain.doFilter(request, response);   // 다음 요청 있으면 실행
        } catch (Exception e){
            log.info("exception ! {}", e.getMessage());
            throw e;
        } finally {
            log.info("REQUEST [{}] [{}]", uuid, requestURI);
        }

    }
}
