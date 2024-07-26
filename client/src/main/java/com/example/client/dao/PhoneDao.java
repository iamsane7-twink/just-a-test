package com.example.client.dao;

import com.example.client.entity.Phone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PhoneDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(value = "tm", propagation = Propagation.REQUIRES_NEW)
    public void save(Phone phone) {
        entityManager.persist(phone);
    }
}
