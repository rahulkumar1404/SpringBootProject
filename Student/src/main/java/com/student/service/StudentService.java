package com.student.service;

import java.util.List;

import com.student.entity.StudentEntity;

public interface StudentService {
	public StudentEntity addStudent(StudentEntity studentEntity);
	public StudentEntity updateStudent(StudentEntity studentEntity,Integer id);
	public List<StudentEntity> viewStudent(StudentEntity studentEntity);

	public StudentEntity viewStudentById(Integer id);
	public StudentEntity deletStudent(Integer id);
	
	

}
