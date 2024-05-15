package model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    private String departmentName;
    List<Student> studentList;
}
