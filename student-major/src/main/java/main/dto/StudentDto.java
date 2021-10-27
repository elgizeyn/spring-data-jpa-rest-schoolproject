package main.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class StudentDto implements Serializable {
    @Size(min = 3, message = "Name's size should be more than 3!")
    private String name;
    @Size(min = 5, message = "Surname size should be more than 5!")
    private String surname;
}
