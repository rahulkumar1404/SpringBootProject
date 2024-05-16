package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.StudentEntity;
import com.student.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	public StudentRepo studentRepo;
	

	@Override
	public StudentEntity addStudent(StudentEntity studentEntity) {
		// TODO Auto-generated method stub
		return studentRepo.save(studentEntity);
	}

	@Override
	public StudentEntity updateStudent(StudentEntity studentEntity, Integer id) {
		// TODO Auto-generated method stub
		StudentEntity student = viewStudentById(id);
		
		student.setName(studentEntity.getName());
		student.setCity(studentEntity.getCity());
		
		return studentRepo.save(student);
	}

	@Override
	public StudentEntity deletStudent( Integer id) {
		// TODO Auto-generated method stub
		StudentEntity entity = studentRepo.findById(id).orElseThrow(null);
		studentRepo.delete(entity);
		return entity;
	}

	@Override
	public StudentEntity viewStudentById(Integer id) {
		StudentEntity entity = studentRepo.findById(id).orElseThrow(null);
		return entity;
	}

	@Override
	public List<StudentEntity> viewStudent(StudentEntity studentEntity) {
		List<StudentEntity> list = studentRepo.findAll();
		
		return list;
	}

}
