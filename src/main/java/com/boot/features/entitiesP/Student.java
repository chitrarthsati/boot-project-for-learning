package com.boot.features.entitiesP;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student",schema = "student_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	@ElementCollection
	@CollectionTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"),schema = "student_data")
	@Column(name = "subject")
	private List<Subjects> subjects;
	
	
	public Student(Long id, String name, Integer age, List<Subjects> subjects) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.subjects = subjects;
	}


	private Long school; 
}



