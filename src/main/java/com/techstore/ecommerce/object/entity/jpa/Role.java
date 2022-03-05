package com.techstore.ecommerce.object.entity.jpa;

import com.techstore.ecommerce.object.constant.SystemRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@SequenceGenerator(name = "role_seq", sequenceName = "roles_id_seq", allocationSize = 1)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private long id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    @Enumerated(EnumType.STRING)
    private SystemRole name;
}
