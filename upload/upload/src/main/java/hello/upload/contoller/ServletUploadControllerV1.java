package hello.upload.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException{
        log.info("request = {}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName = {}", itemName);

        // 여기서 Part가 앞에서 설명한 그 파트
        Collection<Part> parts = request.getParts();
        log.info("parts = {}", parts);

        /*
            Content-Type: multipart/form-data; boundary=----xxxx
            ------xxxx
            Content-Disposition: form-data; name="itemName"
            Spring
            ------xxxx
            Content-Disposition: form-data; name="file"; filename="test.data"
            Content-Type: application/octet-stream
            sdklajkljdf...
         */

        return "upload-form";
    }

}
