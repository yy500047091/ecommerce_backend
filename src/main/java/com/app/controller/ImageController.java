package com.app.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IStorageService;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {
	@Autowired
	private IStorageService storageService;
	
	@RequestMapping(value="/images/{fileName}", produces = "image/*")
	public void downloadFile(@PathVariable("fileName") String fileName,
			HttpServletResponse resp) throws IOException {
		
//		try(FileInputStream in = new FileInputStream("F:/DMC/MobileGalaxy/MobileImages/" + fileName)) {
//			FileCopyUtils.copy(in, resp.getOutputStream());
//		}
		
		FileSystemResource resource = storageService.load(fileName);
		FileCopyUtils.copy(resource.getInputStream(), resp.getOutputStream());
	}
	

	

}