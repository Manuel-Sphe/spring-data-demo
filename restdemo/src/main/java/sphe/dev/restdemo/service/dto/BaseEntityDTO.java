package sphe.dev.restdemo.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntityDTO implements Serializable {

    private UUID id;

    @Override
    public boolean equals(Object other){
        if(this == other)
            return true;
        if(!(other instanceof BaseEntityDTO  baseEntityDTO))
            return false;

        if(this.getId() == null)
            return false;

        return Objects.equals(this.getId(), baseEntityDTO.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.getId());
    }
}
