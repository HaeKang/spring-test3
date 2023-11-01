package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
        addFormatters 를 통해서 사용하고싶은 컨버터를 추가 가능.
        스프링이 내부에서 많은 기본 컨버터를 제공하나, 컨버터를 추가하면 추가한 컨버터가 우선순위를 가짐.
     */

    @Override
    public void addFormatters(FormatterRegistry registry) {
        /*
            포맷터를 추가해야하는데, 우선순위로 인해 아래 두 컨버터가 적용되어버림. 따라서 주석 필수 
            우선순위 : 컨버터 > 포맷터
         */
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 포맷터 추가
        registry.addFormatter(new MyNumberFormatter());
    }

}
