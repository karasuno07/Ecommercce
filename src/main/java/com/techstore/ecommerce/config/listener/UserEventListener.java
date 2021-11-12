package com.techstore.ecommerce.config.listener;

import com.techstore.ecommerce.object.entity.es.UserES;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.repository.es.UserESRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventListener implements
        PostInsertEventListener,
        PostUpdateEventListener,
        PostDeleteEventListener {

    private final UserESRepository repository;
    private final UserMapper mapper;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        if (event.getEntity() instanceof User) {
            UserES user = mapper.jpaToEsEntity((User) event.getEntity());
            repository.save(user);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        if (event.getEntity() instanceof User) {
            UserES user = mapper.jpaToEsEntity((User) event.getEntity());
            repository.save(user);
        }
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        if (event.getEntity() instanceof User) {
            UserES user = mapper.jpaToEsEntity((User) event.getEntity());
            repository.delete(user);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return true;
    }
}
