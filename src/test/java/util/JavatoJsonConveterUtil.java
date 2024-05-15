package util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.College;
import model.Course;
import model.Department;
import model.Student;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class JavatoJsonConveterUtil {
//    college => departmentList => studentList => courseList => courseName and courseId
    Student s1,s2,s3;
    Department d1,d2,d3;
    Course c1,c2,c3;

    @Test
    public void test() throws JsonProcessingException{
        c1 = Course.builder().CourseId(1).courseName("Java").build();
        c1 = Course.builder().CourseId(2).courseName("Python").build();
        c1 = Course.builder().CourseId(3).courseName("Automation Testing").build();
        List<Course> courseList1 = Arrays.asList(c1,c2,c3);
        List<Course> courseList2 = Arrays.asList(c1,c2);
        List<Course> courseList3 = Arrays.asList(c1,c3);

        s1 = Student.builder().courseList(courseList1).name("ABC").address("UK").rollNumber(101).build();
        s2 = Student.builder().courseList(courseList1).name("CDE").address("USA").rollNumber(102).build();
        s3 = Student.builder().courseList(courseList1).name("DEF").address("INDIA").rollNumber(103).build();

        List<Student> studentList1 = Arrays.asList(s1,s2,s3);
        List<Student> studentList2 = Arrays.asList(s1,s3);
        List<Student> studentList3 = Arrays.asList(s1,s2);

        d1 = Department.builder().departmentName("CS").studentList(studentList1).build();
        d2 = Department.builder().departmentName("IT").studentList(studentList2).build();
        d3 = Department.builder().departmentName("CyberSecurity").studentList(studentList3).build();

        List<Department> d = Arrays.asList(d1,d2,d3);
        College c = College.builder().collegeName("MIT").departmentList(d).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String mapped = objectMapper.writeValueAsString(c);
        System.out.println(mapped);
    }

}

