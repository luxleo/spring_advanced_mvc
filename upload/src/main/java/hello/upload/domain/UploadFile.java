package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    // 고객이 올린 파일 이름
    private String uploadFileName;
    // 스토어 저장 하는 이름
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
