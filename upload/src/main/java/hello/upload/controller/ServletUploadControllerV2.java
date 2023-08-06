package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}") // application.properties 의 값을 가져올수 있다.
    private String fileDir;
    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest req) throws ServletException, IOException {
        log.info("request = {}",req);

        String itemName = req.getParameter("itemName");
        log.info("itemName = {}",itemName);

        Collection<Part> parts = req.getParts(); // multipart/formdata이다
        log.info("parts = {}",parts);
        for (Part part : parts) {
            log.info("====PART====");
            log.info("name = {}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {} : {}", headerName, part.getHeader(headerName));
            }
            // 편의 메서드
            // content-disposition; filename


        }
        return "upload-form";
    }
}
