package org.containercraft.servicefilemanager.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.service.files.ContentService;
import org.containercraft.servicefilemanager.service.files.impl.StorageFiles;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class FileAOP {
    private final ContentService contentService;
    private final StorageFiles storageFiles;

    @Before(value = "execution(* org.containercraft.servicefilemanager.controller.FileUploadController.downloadFIle(..))")
    public void fileAccess(JoinPoint joinPoint){
        String filename = (String)joinPoint.getArgs()[0];
        System.out.println("filename:"+filename);

        Optional<Content> content = contentService
                .findBySrc(storageFiles.validPath(filename).toString());

        if(content.isEmpty() || content.get().isDelete()){
            throw new StorageFileNotFoundException("file is not exist");
        }
    }
}
