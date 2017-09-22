package com.example.tutorial3.service;

import java.util.List;
import java.util.ArrayList;
import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {

	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	@Override
	public StudentModel selectStudent(String npm) {
		// TODO Auto-generated method stub
		for (int i = 0; i < studentList.size(); i++)
		{
			if (npm.equals(studentList.get(i).getNpm()))
			{
				return studentList.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<StudentModel> selectAllStudents() {
		// TODO Auto-generated method stub
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		// TODO Auto-generated method stub
		studentList.add(student);
	}
	
	@Override
	public boolean checkByNPM(String npm)
	{
		for (int i = 0; i < studentList.size(); i++)
		{
			if (npm.equals(studentList.get(i).getNpm()))
			{
				
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteStudent(String npm) {
		// TODO Auto-generated method stub
		for (int i = 0; i < studentList.size(); i++)
		{
			if (npm.equals(studentList.get(i).getNpm()))
			{
				studentList.remove(i);
				return true;
			}
		}
		return false;
	}

}
