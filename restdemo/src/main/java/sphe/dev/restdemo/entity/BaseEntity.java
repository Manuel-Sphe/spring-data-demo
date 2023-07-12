package sphe.dev.restdemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@Data
@SuperBuilder
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updated;

    protected BaseEntity(){}

    public final BaseEntity id(UUID id){
        this.setId(id);
        return this;
    }

    /**
     *
     * @param other object
     * @return boolean
     */
    @Override
    public boolean equals(Object other){
        if(this == other)
            return true;

        if(other == null  || getClass() != other.getClass() )
            return  false;

        BaseEntity that = (BaseEntity) other;
        return java.util.Objects.equals(id, that.id);

    }


}
