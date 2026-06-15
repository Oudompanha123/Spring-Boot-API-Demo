package com.ppcb.cafemerchant.common.service.storage;

import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import com.ppcb.cafemerchant.common.components.exception.BusinessException;
import com.ppcb.cafemerchant.common.config.properties.FileInfoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileStorageService {

    private final FileInfoProperties fileInfoProperties;

    public String storeImage(MultipartFile file, String oldFileName) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(StatusCode.FILE_NOT_FOUND);
        }

        // 10MB = 10 * 1024 * 1024 = 10485760 bytes
        long maxFileSize = 10 * 1024 * 1024; // 10MB
        if (file.getSize() > maxFileSize) {
            throw new BusinessException(StatusCode.FILE_SIZE_EXCEED);
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isBlank()) {
            throw new BusinessException(StatusCode.INVALID_FILE_NAME);
        }

        String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
        String newFileName = UUID.randomUUID() + "." + extension;

        File targetDir = new File(fileInfoProperties.getServerPath());
        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new BusinessException(StatusCode.FAIL_UPLOAD_FILE);
        }

        if (oldFileName != null && !oldFileName.isBlank()) {
            File oldFile = new File(targetDir, oldFileName);
            if (oldFile.exists() && !oldFile.delete()) {
                throw new BusinessException(StatusCode.FAIL_REMOVE_FILE);
            }
        }

        File newFile = new File(targetDir, newFileName);
        Files.copy(file.getInputStream(), newFile.toPath());

        return newFileName;
    }

}
