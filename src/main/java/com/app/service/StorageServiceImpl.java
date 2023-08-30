package com.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class StorageServiceImpl implements IStorageService {
	
	
	
	@Value("${disk.upload.basepath}")
	private String BASEPATH; 

	
	@Override
	public List<String> loadAll() {
		File dirPath = new File(BASEPATH);
		return Stream.of(dirPath.list()).collect(Collectors.toList());
	}

	@Override
	public String store(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		File filePath = new File(BASEPATH, fileName);
		try(FileOutputStream out = new FileOutputStream(filePath)) {
			FileCopyUtils.copy(file.getInputStream(), out);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public FileSystemResource load(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		System.out.println("Loading file: " + filePath.getAbsolutePath());
		if(filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}

	@Override
	public void delete(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		if(filePath.exists())
			filePath.delete();
	}

}
