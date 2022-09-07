package com.ernesto.springboot.gsm.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ernesto.springboot.gsm.models.entity.Articulo;
import com.ernesto.springboot.gsm.models.service.IArticuloService;
import com.ernesto.springboot.gsm.models.service.IUploadFileService;

/*
 * Tomado como referencia para subir imagenes
 *	https://www.bezkoder.com/spring-boot-file-upload/ 
 */

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
	
	private static final String ARTICULO = "articulo";
	
	private static Logger logger = LoggerFactory.getLogger(ArticuloController.class);
	
	private String mensajeLog;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private IArticuloService articuloService;
	
	@GetMapping("/articulos")
	public List<Articulo> articulos() {
		logger.info("En: articulos() ");
		return articuloService.findAll();	
	}
	
	@GetMapping("/jpa")
	public List<Articulo> articulosJpa() {
		logger.info("En: articulosJpa() ");
		return articuloService.fetchAllWithProveedorWithRubroJpa();	
	}

	@GetMapping("/nq")
	public List<Articulo> articulosNq() {
		logger.info("En: articulosNq() ");
		return articuloService.fetchAllWithProveedorWithRubroNQ();	
	}
	
	@GetMapping("/articulo-por-id/{id}")
	public Articulo articuloPorId(@PathVariable Long id) {
		
		mensajeLog = String.format("En: articuloPorId() - id: %s ", id.toString());
		logger.info(mensajeLog);
		
		return articuloService.findById(id);	
	}
	
	@GetMapping("/articulo-por-descripcion/{descripcion}")
	public List<Articulo> articuloPorDescripcion(@PathVariable String descripcion) {
		
		mensajeLog = String.format("En: articuloPorDescripcion() - descripcion: %s ", descripcion);
		logger.info(mensajeLog);
		return articuloService.findByDescripcionLike(descripcion);
	}
	
	@GetMapping("/articulo-por-codigo-fabrica/{codigoFabrica}")
	public List<Articulo> articuloPorCodigoFabrica(@PathVariable String codigoFabrica) {
		
		mensajeLog = String.format("En: articuloPorCodigoFabrica() - codigoFabrica: %s ", codigoFabrica);
		logger.info(mensajeLog);
		return articuloService.findByCodigoFabricaLike(codigoFabrica);	
	}
	
	@PostMapping("/articulo")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo add(@RequestBody Articulo articulo) {
		
		
		mensajeLog = String.format("En: add() - articulo: %s ", articulo.toString());
		logger.info(mensajeLog);
		
		return articuloService.save(articulo);
	}
	
	@PutMapping("/articulo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo update(@PathVariable Long id, @RequestBody Articulo articulo) {
		
		mensajeLog = String.format("En: update() - id: %s ", id.toString());
		logger.info(mensajeLog);
		
		if (articuloService.existsById(id)) {
			return articuloService.save(articulo);
		} else {
			return null;
		}
	}

	@DeleteMapping("/articulo/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		mensajeLog = String.format("En: delete() - id: %s ", id.toString());
		logger.info(mensajeLog);
		articuloService.delete(id);
	}

	/*
	 *	Manejo de imagenes 
	 */
	@PostMapping("/guardar-imagen/{id}")
	public ResponseEntity<Map<String,String>> guardarImagen(@PathVariable Long id, @RequestParam("imagen") MultipartFile imagen) {
		logger.info("En: guardarImagen()");

		Map<String, String> response = new HashMap<>();
		
		Articulo articulo = articuloService.findById(id);
		
		if (articulo == null) {
			response.put("error", "Hubo un error al guardar la imagen!. No existe el articulo!. NombreArchivo: " + imagen.getOriginalFilename());
			return ResponseEntity
						.status(HttpStatus.EXPECTATION_FAILED)
						.body(response);
		}
		
		if (articulo.getImagen() != null && articulo.getImagen().length() > 0) {
			uploadFileService.delete(articulo.getImagen(), ARTICULO);			
		}
		
		try {

			String nombreImagen = uploadFileService.save(imagen, ARTICULO);
			response.put("ok", "La imagen se guardo con éxito!. Full Path: " + nombreImagen + " Nombre archivo: " + imagen.getOriginalFilename());
			
			articulo.setImagen(nombreImagen);
			articuloService.save(articulo);
			
			//articuloService.updateImagenById(id, nombreImagen);
			
			return ResponseEntity
					.ok()
					.body(response);
		} catch (IOException e) {
			response.put("error", "Hubo un error al guardar la imagen!. NombreArchivo: " + imagen.getOriginalFilename() + " Mensaje Error: " + e.getMessage());
			return ResponseEntity
						.status(HttpStatus.EXPECTATION_FAILED)
						.body(response);
		}
	}
	
	@GetMapping("/ver-imagen/{filename:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
		logger.info("En: verImagen()");

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename, ARTICULO);
			
			if (recurso == null) {
				recurso = new UrlResource(Paths.get("C://uploads//no-img.jpg").toUri());	
			} 
			
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_GIF)
					.contentType(MediaType.IMAGE_PNG)
					.contentType(MediaType.IMAGE_JPEG)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attacment; filename=\"" + recurso.getFilename() + "\"")
					.body(recurso);
			
		} catch (MalformedURLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return null;

	}
}