package com.techstore.ecommerce.object.entity.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Document(indexName = "users")
@Data
public class UserES {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Keyword)
    private String password;

    @Field(name = "full_name", type = FieldType.Text)
    private String fullName;

    @Field(type = FieldType.Boolean)
    private Boolean gender;

    @Field(name = "date_of_birth", type = FieldType.Date)
    private Date dateOfBirth;

    @Field(name = "phone_number", type = FieldType.Boolean)
    private String phoneNumber;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Text)
    private String image;

    @Field(type = FieldType.Boolean)
    private Boolean active;

    @Field(type = FieldType.Nested, includeInParent = true, storeNullValue = true)
    private RoleES role;
}
