package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    // parse를 통해 문자를 숫자로 변환
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text = {}, local = {}", text, locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);  
    }

    // print를 통해 객체를 문자로 변환
    @Override
    public String print(Number object, Locale locale) {
        log.info("object = {}, locale = {}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
    
}
