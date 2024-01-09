package com.boot.features.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.boot.features.db1Repo.JPAStudentRepo;
import com.boot.features.entitiesP.Student;
import com.boot.features.exceptions.InvalidDataException;
import com.boot.features.utils.ProjectUtils;
import com.boot.features.utils.StudentBean;
import com.boot.features.utils.StudentDTO;

import jakarta.transaction.Transactional;

@RestController
public class StudentController {

	@Autowired
	private SessionFactory factory;

//	@Autowired
//	@Qualifier(value = "elasticsearchStudentRepo")
//	private ElasticSearchStudentRepo elasticRepo;

	@Autowired
	private JPAStudentRepo jpaRepo;

	@Autowired
	private ProjectUtils utils;

	@GetMapping("/test")
	public Map<String, String> testApplication() {

		HashMap<String, String> map = new HashMap<>();
		map.put("status", "application working successfully");
		return map;
	}

	@PostMapping("/addStudents")
	@Transactional
	public ResponseEntity<?> addStudent(@RequestBody List<Student> s) {

//		elasticRepo.saveAll(s);
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			for (int i = 0; i < s.size(); i++) {
				Student st = s.get(i);
				sess.save(st);
//				if(s.get(i) == s.get(2)) {
//					Student std = s.get(2);
//					// creating an exception case so that no transaction gets committed and rollback is performed.
//					std.setAge(Integer.valueOf("acbc")); 
//					sess.save(std);
//				}

			}
//			never commit inside the loop
			tx.commit();
		} catch (Exception e) {
//			 tx.rollback();
			e.printStackTrace();
		}
		sess.close();
//		jpaRepo.saveAll(s);
		return ResponseEntity.ok("done.. check db");

	}

//	JPA (Spring boot)
	@GetMapping("/studentsUsingJPA/{offset}")
	public ResponseEntity<?> getStudentsUsingJPA(@PathVariable("offset") Integer offset,
			@RequestParam(value = "filterBy", required = false) String value) {

//		pagination and sorting also
		Integer numberOfElements = 10;
		if (offset <= 0 || offset == null)
			return new ResponseEntity<>("please provide correct page number", HttpStatus.BAD_REQUEST);
		else {
			PageRequest pr = PageRequest.of(offset - 1, numberOfElements);
			if (value != null)
				pr.withSort(Sort.by(Direction.DESC, value));
			return new ResponseEntity<>(jpaRepo.findAll(pr), HttpStatus.OK);
		}

	}

//	HQL 
	@SuppressWarnings({ "unchecked", "deprecation" })
	@GetMapping("/studentsUsingHQL")
	@Transactional
	public ResponseEntity<?> getStudentsUsingHQL() {

		Session session = factory.openSession();
		Query q = session.createQuery("select s from Student s");
		q.setFirstResult(1);
		List<Student> l = (List<Student>) q.getResultList();
		q.setMaxResults(l.size() - 1);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

//	JPQL
	@SuppressWarnings("deprecation")
	@GetMapping("subjectsByJPQL/{studentId}")
	public ResponseEntity<?> getSubjectsForStudentId(@PathVariable("studentId") Integer id) {

		Session session = factory.openSession();
		Query q = session.createQuery("select s from Student s where s.id =:id"); // also called jpql
		q.setParameter("id", id);
		return new ResponseEntity<>((Student) q.getSingleResultOrNull(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/studentsByAge")
	public Object getStudentsByAge(@RequestParam("offset") Integer offset,
			@RequestParam("age") Integer age) {
		
		List<Student> list = jpaRepo.findByAge(age, PageRequest.of(offset, 10));
		if (list.size() > 0)
			System.out.println("student are there");
		else
			System.out.println("students are not there!!!!!!!!1");
		return new ResponseEntity<>(jpaRepo.findByAge(age, PageRequest.of(offset, 10)), HttpStatus.OK);
	}

	@GetMapping("/studentsAll")
	public Object getStudentsAndSchoolsInformation() {

		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> entity = rt.getForEntity("http://localhost:9090/school", String.class);
//		System.out.println("entity resullt = " + entity.getBody());
//		System.out.println(utils.strToJson(entity.getBody()));
		List<HashMap<String, Object>> schoolList = utils.strToJson(entity.getBody());
		List<StudentDTO> studentList = jpaRepo.getAll();
		List<StudentBean> result = studentList.stream()
				.map(st -> new StudentBean(st.getId(), st.getName(), st.getAge(), st.getSchool(), List.of(st.getSubjects().split(","))))
				.collect(Collectors.toList());
		Map<String, Object> output = new HashMap<>();
		output.put("students", result);
		output.put("schools", schoolList);
		return output;
	}
	
	@PostMapping("/student-admission")
	public ResponseEntity<?> configureStudentWithSchool(@RequestParam(value = "s_id",required = true) Long studentId,
				@RequestParam(value = "school_id", required = true) Long schoolId) {
		
		Student st = jpaRepo.findById(studentId).orElseThrow(
							() -> new RuntimeException("student not found with id " + studentId));
		if(st.getSchool() != null)
			return new ResponseEntity<>("student already studying in school", HttpStatus.BAD_REQUEST);
		else {
			st.setSchool(schoolId);
			return new ResponseEntity<>(jpaRepo.save(st), HttpStatus.OK);
			}
		
	}
}
