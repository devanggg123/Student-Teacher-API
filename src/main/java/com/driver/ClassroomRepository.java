package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class ClassroomRepository {

    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, Student> studentMap;
    private HashMap<String, List<String>> teacherstudentMapping;

    public ClassroomRepository() {
        this.teacherMap = new HashMap<String, Teacher>();
        this.studentMap = new HashMap<String, Student>();
        this.teacherstudentMapping = new HashMap<String, List<String>>();
    }
    public void saveStudent( Student student){
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(), teacher);
    }
    public void saveStudentTeacher(String student, String teacher) {

        //1. Add the movie into Datbase ---> WRONG bcz I dont have te movie object

        if (studentMap.containsKey(student) && teacherMap.containsKey(teacher)) {

            List<String> currentSttudentByTeacher = new ArrayList<>();

            if (teacherstudentMapping.containsKey(teacher))
                currentSttudentByTeacher = teacherstudentMapping.get(teacher);

            currentSttudentByTeacher.add(student);

            teacherstudentMapping.put(teacher, currentSttudentByTeacher);

        }
    }
    public Student findStudent(String student){
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }

    public List<String> findstudentFromTeacher(String teacher){
        List<String> studentList = new ArrayList<String>();
        if(teacherstudentMapping.containsKey(teacher)) studentList = teacherstudentMapping.get(teacher);
        return studentList;
    }
    public List<String> findAllStudent(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){

        List<String> students = new ArrayList<String>();
        if(teacherstudentMapping.containsKey(teacher)){
            //1. Find the movie names by director from the pair
            students = teacherstudentMapping.get(teacher);

            //2. Deleting all the movies from movieDb by using movieName
            for(String student: students){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }

            //3. Deleteing the pair
            teacherstudentMapping.remove(teacher);
        }

        //4. Delete the director from directorDb.
        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
    }
    public void deleteAllTeacher(){

        HashSet<String> studentsSet = new HashSet<String>();

        //Deleting the director's map
        teacherMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String teacher: teacherstudentMapping.keySet()){

            //Iterating in the list of movies by a director.
            for(String student: teacherstudentMapping.get(teacher)){
                studentsSet.add(student);
            }
        }

        //Deleting the movie from the movieDb.
        for(String student: studentsSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }
        //clearing the pair.
        teacherstudentMapping = new HashMap<>();
    }

}
