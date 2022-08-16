package com.ernesto.springboot.gsm.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private static final Logger logger = LoggerFactory.getLogger(IUploadFileService.class); 
	
	private static final String UPLOAD_ROOT_FOLDER = "C://uploads"; 
	
	@Override
	public void init() throws IOException {
		Files.createDirectories(Paths.get(UPLOAD_ROOT_FOLDER));	
	}

	@Override
	public String save(MultipartFile file, String carpeta) throws IOException {

		/*
		 * Armamos el nombre de archivo con un numero random + el nombre original
		 */
		String nombreUnicoArchivo = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

		/*
		 * Obtenemos el path absoluto con el nombre + la carpeta correspondiente 
		 */
		Path pathAbsoluto = getPath(nombreUnicoArchivo, carpeta);
		
		logger.info("En: save() - pathAbsoluto: " + pathAbsoluto.toString());

		/*
		 * Copiamos en el disco
		 */
		Files.copy(file.getInputStream(), pathAbsoluto);
		
		return nombreUnicoArchivo;
	}

	@Override
	public Resource load(String filename, String carpeta) throws MalformedURLException {

		Path pathImagen = getPath(filename, carpeta);
		
		logger.info("En: load() - pathImagen: " + pathImagen.toString());
		
		Resource recurso = new UrlResource(pathImagen.toUri());
		
		if (!recurso.exists() || !recurso.isReadable()) {
			//throw new RuntimeException("Error: El archivo esta corrupto: " + pathImagen.toString());	
			return null; 
		}
				
		return recurso;
	}

	@Override
	public boolean delete(String filename, String carpeta) {
		
		Path pathImagen = getPath(filename, carpeta);
		
		File archivoImagen = pathImagen.toFile();
		
		if (archivoImagen.canRead() && archivoImagen.exists()) {
			if (archivoImagen.delete()) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Stream<Path> loadAll() throws IOException {

		return Files
			.walk(Paths.get(UPLOAD_ROOT_FOLDER), 1)
			.filter(path -> !path.equals(Paths.get(UPLOAD_ROOT_FOLDER)))
			.map(Paths.get(UPLOAD_ROOT_FOLDER)::relativize)
		;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_ROOT_FOLDER).toFile());
	}

	/*
	 * Metodo auxiliar para armar el path
	 */
	public Path getPath(String filename, String carpeta) {
		return Paths.get(UPLOAD_ROOT_FOLDER + "//" + carpeta).resolve(filename).toAbsolutePath(); 
	}

}
