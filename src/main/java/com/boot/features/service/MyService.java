package com.boot.features.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.boot.features.db1Repo.JPAStudentRepo;
import com.boot.features.db2Repo.AddressRepo;
import com.boot.features.entitiesP.Student;
import com.boot.features.entitiesS.Address;

@Service
public class MyService {

	@Autowired
	@Qualifier(value = "jpastudentrepo")
	private JPAStudentRepo studentRepo;
	
	@Autowired
	private AddressRepo addressRepo;

	private Long studentId;
	@Autowired
	@Qualifier("primaryTransactionManager")
	private PlatformTransactionManager primaryTransactionManager;

	@Autowired
	@Qualifier("secondaryTransactionManager")
	private PlatformTransactionManager secondaryTransactionManager;

	@Transactional(transactionManager = "primaryTransactionManager")
	public Student saveStudent(Student s) {
		// Perform operations on the primary data source
		Student st = studentRepo.save(s);
		studentId = st.getId();
		return st;
	}

	@Transactional(transactionManager = "secondaryTransactionManager")
	public Address saveAddress(Address add) {
		// Perform operations on the secondary data source
		add.setStudentId(studentId);
		return addressRepo.save(add);
	}
}
