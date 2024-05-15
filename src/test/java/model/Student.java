package model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    private String name;
    private String address;
    private int rollNumber;
    private List<Course> courseList;
}
