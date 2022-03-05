package com.techstore.ecommerce.config;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

public class CustomPostgresqlDialect extends PostgreSQL10Dialect {

    public CustomPostgresqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
