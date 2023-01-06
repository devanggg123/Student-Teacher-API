package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    public void addStudent(Student student){
        classroomRepository.saveStudent(student);
    }
    public void addTeacher(Teacher teacher){
        classroomRepository.saveTeacher(teacher);
    }
    public void createTeacherStudentPair(String student, String teacher){
        classroomRepository.saveStudentTeacher(student, teacher);
    }

    public Student findStudent(String Studentname){
        return classroomRepository.findStudent(Studentname);
    }
    public Teacher findTeacher(String TeacherName){
        return classroomRepository.findTeacher(TeacherName);
    }

    public List<String> findStudentFromTeacher(String teacher){
        return classroomRepository.findstudentFromTeacher(teacher);
    }

    public List<String> findAllStudent(){
        return classroomRepository.findAllStudent();
    }

    public void deleteTeacher(String teacher){
        classroomRepository.deleteTeacher(teacher);
    }

    public void deleteAllTeacher(){
        classroomRepository.deleteAllTeacher();
    }

}
