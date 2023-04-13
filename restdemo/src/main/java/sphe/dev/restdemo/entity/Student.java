package sphe.dev.restdemo.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    public UUID id;
    public String firstName, secondName, email ; 
}
