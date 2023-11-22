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
    
    public String getFullPath(String filename){
        return fileDir + filename;
    }
    
    // 여러 파일 저장
    public List<UploadFile> storeFiles(List<MultipartFile> muiltipartFiles) throws IOException{
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile muiltipartFile : muiltipartFiles) {
            if(!muiltipartFile.isEmpty()){
                storeFileResult.add(storeFile(muiltipartFile));
            }
        }
        
        return storeFileResult;
        
    }
    
    // 단일 파일 저장
    public UploadFile storeFile(MultipartFile muiltipartFile) throws IOException{
        if (muiltipartFile.isEmpty()){
            return null;
        }
        
        String originalFilename = muiltipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        muiltipartFile.transferTo(new File(getFullPath(storeFileName)));
        
        return new UploadFile(originalFilename, storeFileName);
    }
    

    // UUID를 통해 서버 저장 파일명 생성하기
    private String createStoreFileName(String originalFilename){
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자만 얻기
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }


}
