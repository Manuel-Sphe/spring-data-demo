package sphe.dev.restdemo.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@SuperBuilder
public class StudentDTO extends BaseEntityDTO{

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }


    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public boolean equals(Object other){
        if(this == other)
            return true;
        if(!(other instanceof StudentDTO studentDTO))
            return false;

        return Objects.equals(this.getId(), studentDTO.getId());
    }
}
