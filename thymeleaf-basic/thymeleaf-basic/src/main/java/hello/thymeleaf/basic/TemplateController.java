package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragement")
    public String template(){
        return "template/fragement/fragementMain";
    }

    @GetMapping("/layout")
    public String layout(){
        return "templaye/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtend(){
        return "template/layoutExtend/layoutExtendMain";
    }
}
