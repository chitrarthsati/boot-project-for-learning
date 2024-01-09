//package com.boot.features.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.boot.features.entities.Student;
//import com.boot.features.entities.StudentDocument;
//import com.boot.features.repo.ElasticSearchStudentRepo;
//import com.boot.features.repo.JPAStudentRepo;
//
//import jakarta.persistence.PostPersist;
//
//@Service
//public class SynchronizationService {

//	private JPAStudentRepo jpaRepo;
//	private ElasticSearchStudentRepo elasticRepo;
//	
//	@Autowired
//	public SynchronizationService(JPAStudentRepo jpaRepo, ElasticSearchStudentRepo elasticRepo) {
//		super();
//		this.jpaRepo = jpaRepo;
//		this.elasticRepo = elasticRepo;
//	}
//	
//	@PostPersist
//    public void syncOnCreate(List<Student> students) {
//        // Create a new BookDocument and save it to Elasticsearch
//		List<StudentDocument> l = new ArrayList<>();
//		for(Student s : students) {
//			StudentDocument st = new StudentDocument();
//			st.setName(s.getName());
//			st.setAge(s.getAge());
//			st.setSubjects(s.getSubjects());
//			l.add(st);
//		} elasticRepo.saveAll(l);
//		
//    }

//    @PostUpdate
//    public void syncOnUpdate(Book book) {
//        // Update the corresponding BookDocument in Elasticsearch
//        Optional<BookDocument> existingDocument = bookDocumentRepository.findById(book.getId());
//        if (existingDocument.isPresent()) {
//            BookDocument bookDocument = existingDocument.get();
//            bookDocument.setTitle(book.getTitle());
//            bookDocumentRepository.save(bookDocument);
//        }
//    }

//    @PostRemove
//    public void syncOnDelete(Book book) {
//        // Delete the corresponding BookDocument from Elasticsearch
//        bookDocumentRepository.deleteById(book.getId());
//    }
//}
