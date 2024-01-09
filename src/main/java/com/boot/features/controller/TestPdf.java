package com.boot.features.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pdf")
public class TestPdf {

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> handleFileUpload(
            @RequestParam("fileName") String fileName,
            @RequestParam("companyId") String companyId,
            @RequestParam("subscriberId") String subscriberId,
            @RequestPart("file") MultipartFile file) {
		
//		System.out.println(contentType);
		System.out.println("content type is " + file.getContentType());
		System.out.println("file name is " + file.getOriginalFilename());
		
		return file.isEmpty() || file.getContentType() != "application/pdf"
				? ResponseEntity.badRequest().body("PDF File is Required")
				: ResponseEntity.ok().body("file uploaded successfully!!!");

	}
	
	
}
