package com.boot.features.db1Repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.features.entitiesP.Student;
import com.boot.features.utils.StudentDTO;

@Repository(value = "jpastudentrepo")
public interface JPAStudentRepo extends JpaRepository<Student, Long>, PagingAndSortingRepository<Student, Long>{

	
	@Query(value = "select s.* from test.student s where s.age =:age", nativeQuery = true)
	List<Student> findByAge(@Param("age") Integer age,Pageable pageable);
	
	@Query(value = "select s.id,s.name,s.age,s.school,string_agg(sub.subject_name, ',') as subjects \r\n"
			+ "from student_data.student s\r\n"
			+ "inner join student_data.student_subjects sub on sub.student_id=s.id\r\n"
			+ "group by s.id order by s.id", nativeQuery = true)
	List<StudentDTO> getAll();
	
//	Optional<Student> findById(Long id);
	
	
//	List<StudentDTO> findByAgeBetween(Integer age1, Integer age2);
	
	
}
