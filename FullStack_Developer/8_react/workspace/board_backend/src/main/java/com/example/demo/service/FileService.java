package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {
	ResponseEntity<Resource> getThumbnailResource(String systemname) throws Exception;
	ResponseEntity<Resource> downloadFile(String systemname) throws Exception;
}
