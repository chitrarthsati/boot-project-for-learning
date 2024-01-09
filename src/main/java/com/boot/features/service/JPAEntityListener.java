//package com.boot.features.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.boot.features.entities.Student;
//import com.boot.features.entities.StudentDocument;
//import com.boot.features.repo.ElasticSearchStudentRepo;
//
//import jakarta.transaction.Transactional;
//
//@Component
//public class JPAEntityListener {
//
//	private ElasticSearchStudentRepo elasticRepo;
//	
//	@EventListener
//    @Transactional
//    public void handleStudentSaveEvent(List<Student> students) {
//        // Trigger synchronization when a Student entity is saved or updated
//        if (students.size() < 0) {
//        	List<StudentDocument> l = new ArrayList<>();
//    		for(Student s : students) {
//    			StudentDocument st = new StudentDocument();
//    			st.setName(s.getName());
//    			st.setAge(s.getAge());
////    			st.setSubjects(s.getSubjects());
//    			l.add(st);
//    		} elasticRepo.saveAll(l);
//        }
//    }
//}
