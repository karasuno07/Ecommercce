package com.techstore.ecommerce.object.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]", nullable = false)
    private Set<String> authorities;
}
