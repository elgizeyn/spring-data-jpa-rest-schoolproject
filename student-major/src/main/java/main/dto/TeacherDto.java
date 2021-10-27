package main.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class TeacherDto implements Serializable {
    private String name;
    private String surname;
}
