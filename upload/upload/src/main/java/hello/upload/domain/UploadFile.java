package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;  // 본파일명
    private String storeFileName;   // 서버에 저장되는 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}
