package org.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="leads")
public class LeadEntity {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String email;
    private String phone;
}
