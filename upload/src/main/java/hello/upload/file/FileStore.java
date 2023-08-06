package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String path) {
        return fileDir + path;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> res = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFiles.isEmpty()) {
                res.add(storeFile(multipartFile));
            }
        }
        return res;
    }
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = genStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }

    private String genStoreFileName(String originalFilename) {
        String ext = getExtension(originalFilename);

        //uuid -> 스토어에 저장시 편하게
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private String getExtension(String originalFilename) {
        // 확장자추출
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos);
    }
}
