package com.app.service;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	String store(MultipartFile file);

	FileSystemResource load(String fileName);
	List<String> loadAll();
	void delete(String fileName);

}
