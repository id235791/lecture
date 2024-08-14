package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.FileService;


@RequestMapping("/api/file/*")
@Controller
public class FileController {
	@Autowired
	private FileService service;
	
	@GetMapping("thumbnail/{systemname}")
	public ResponseEntity<Resource> thumbnail(@PathVariable("systemname") String systemname) throws Exception {
		System.out.println(systemname);
		return service.getThumbnailResource(systemname);
	}
	
	@GetMapping("download/{systemname}")
	public ResponseEntity<Resource> download(@PathVariable("systemname") String systemname) throws Exception{
		return service.downloadFile(systemname);
	}
}





