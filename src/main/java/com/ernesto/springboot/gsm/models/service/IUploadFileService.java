package com.ernesto.springboot.gsm.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	/*
	 * Inicializa la carpeta de imagenes.
	 */
	public void init() throws IOException;
	
	public String save(MultipartFile file, String carpeta) throws IOException;
	
	public Resource load(String filename, String carpeta) throws MalformedURLException;
	
	public boolean delete(String filename, String carpeta);
	
	public Stream<Path> loadAll() throws IOException; 
	
	public void deleteAll();
	
	
}
