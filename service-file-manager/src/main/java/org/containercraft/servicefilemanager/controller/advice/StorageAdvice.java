package org.containercraft.servicefilemanager.controller.advice;

import org.containercraft.servicefilemanager.dto.MessageError;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StorageAdvice {
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.status(404).body(new MessageError(exc.getMessage()));
    }
}
