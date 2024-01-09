package com.boot.features.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.features.entitiesP.Student;
import com.boot.features.entitiesP.Subjects;
import com.boot.features.exceptions.InvalidDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/practice")
public class Practice {

	private static ObjectMapper mapper;

	
	public static void main(String[] args) {

		javaObjectToJson(new Student(10L,"good name",90,
				List.of(new Subjects("english")
				,new Subjects("maths")
				,new Subjects("hindi")
				,new Subjects("german")
				,new Subjects("java"))));
//		System.out.println(output);
	}

	public static void writeJsonToFile(String data) {
		try {
			mapper.writeValue(new File("C:/Users/csati/Desktop//abc.text"), data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/mapper")
	public static Map<String, Object> javaObjectToJson(@RequestBody Student data) {

		mapper = new ObjectMapper();
		try {
			writeJsonToFile(data.toString());
//			return mapper.writeValueAsString(Integer.valueOf("error aja"));
			String resultant = mapper.writeValueAsString(data);
			System.out.println(resultant);
			System.out.println(getMapFromJson(resultant));
			return getMapFromJson(resultant);
		} catch (JsonProcessingException | NumberFormatException e) {
			e.printStackTrace();
			return (Map<String, Object>) new HashMap<String,Object>().put("msg", "agyi error");
		}
	
	}
	
	public static Map<String,Object> getMapFromJson(String data){
		try {
			return mapper.readValue(data, new TypeReference<Map<String,Object>>() {
			});
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
