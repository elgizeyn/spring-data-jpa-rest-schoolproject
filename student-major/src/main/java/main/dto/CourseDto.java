package main.dto;

import lombok.*;
import java.io.Serializable;

@Data
public class CourseDto implements Serializable {
    private String name;
    private String content;
}
