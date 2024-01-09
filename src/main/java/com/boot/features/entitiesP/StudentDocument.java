package com.boot.features.entitiesP;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Document(indexName = "students")
public class StudentDocument {


		@Id
		private Long id;
		private String name;
		private Integer age;
		@ElementCollection
		@CollectionTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"))
		@Column(name = "subject")
		private List<Subjects> subjects;
	}

