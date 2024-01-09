package com.boot.features.utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentBean {

	private Long id;
	private String name;
	private Integer age;
	private Long schoolId;
	private List<String> subjects;
}
