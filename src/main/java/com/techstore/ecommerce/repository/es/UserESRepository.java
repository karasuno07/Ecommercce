package com.techstore.ecommerce.repository.es;

import com.techstore.ecommerce.object.entity.es.UserES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserESRepository extends ElasticsearchRepository<UserES, Long> {

    Optional<UserES> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String Email);

    boolean existsByPhoneNumber(String phoneNumber);
}
