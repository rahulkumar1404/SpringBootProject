package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.StudentEntity;
import com.student.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	public StudentService studentService;
	
	@PostMapping("/add")
	public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentEntity studentEntity){
		StudentEntity student = studentService.addStudent(studentEntity);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity studentEntity, @PathVariable Integer id){
		StudentEntity entity = studentService.updateStudent(studentEntity, id);
		return new ResponseEntity<StudentEntity>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StudentEntity>> viewStudent(@RequestBody StudentEntity studentEntity){
		List<StudentEntity> entities = studentService.viewStudent(studentEntity);
		
		return new ResponseEntity<List<StudentEntity>>(entities,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentEntity> viewStudentById(@PathVariable("id") Integer id){
		StudentEntity studentEntity = studentService.viewStudentById(id);
		return new ResponseEntity<StudentEntity>(studentEntity,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<StudentEntity> deletStudent(@PathVariable("id") Integer id){
		StudentEntity deletStudentEntity = studentService.deletStudent(id);
		return new ResponseEntity<StudentEntity>(deletStudentEntity,HttpStatus.OK);
	}
}
