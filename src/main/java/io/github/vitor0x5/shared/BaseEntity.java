package io.github.vitor0x5.shared;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @Type(type = "pg-uuid")
    private UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt = new Date();
}
