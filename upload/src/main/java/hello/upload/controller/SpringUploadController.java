package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}") // application.properties 의 값을 가져올수 있다.
    private String fileDir;
    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file) throws IOException {
        log.info("itemName = {}", itemName);
        log.info("file = {}", file);

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename(); // getOriginalFileName -> 사용자가 업로드한 파일명
            log.info("file saved at = {}", fullPath);
            file.transferTo(new File(fullPath)); // 파일저장
        }
        return "upload-form";
    }
}
