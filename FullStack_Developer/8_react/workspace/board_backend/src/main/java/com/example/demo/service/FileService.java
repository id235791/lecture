package com.example.demo.service;

import java.util.HashMap;

import org.springframework.core.io.Resource;

public interface FileService {
	HashMap<String, Object> getTumbnailResource(String systemname) throws Exception;
	HashMap<String, Object> downloadFile(String systemname) throws Exception;
}
