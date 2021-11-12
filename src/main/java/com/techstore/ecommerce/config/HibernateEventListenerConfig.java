package com.techstore.ecommerce.config;

import com.techstore.ecommerce.config.listener.UserEventListener;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Configuration
@RequiredArgsConstructor
public class HibernateEventListenerConfig {

    @PersistenceUnit
    private final EntityManagerFactory factory;

    private final UserEventListener userListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = factory.unwrap(SessionFactoryImpl.class);

        EventListenerRegistry registry = sessionFactory.getServiceRegistry()
                                                       .getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_INSERT)
                .appendListeners(userListener);
        // thêm listener khác thì phẩy sau cái user listener rồi thêm vô
        registry.getEventListenerGroup(EventType.POST_UPDATE)
                .appendListeners(userListener);
        registry.getEventListenerGroup(EventType.POST_DELETE)
                .appendListeners(userListener);
    }
}
