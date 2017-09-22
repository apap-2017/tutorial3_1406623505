package com.example.tutorial3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.model.StudentModel;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController()
	{
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm", required = false) String npm){
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student", student);
		if (npm == null)
		{
			return "npmempty";
		}
		return "view";
		
	}
	
	@RequestMapping(value = "/student/view/{npm}", method=RequestMethod.GET)
	public String viewnpm(@PathVariable String npm , Model model){
		if (studentService.checkByNPM(npm))
		{
			return "view";
		}
		return "nostudent";
		
	}
	
	
	@RequestMapping(value = "/student/delete/{npm}", method=RequestMethod.GET)
	public String deletenpm(@PathVariable String npm , Model model){
		boolean check = studentService.deleteStudent(npm);
		
		if (check == true)
		{
			return "deletesuccess";
		}
		else
		{
			return "deleteerror";
		}
		
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model){
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm, 
			@RequestParam(value = "name", required = true) String name, 
			@RequestParam(value = "gpa", required = true) double gpa)
	{
		StudentModel student = new StudentModel(npm, name, gpa);
		studentService.addStudent(student);
		return "add";
	}
}
