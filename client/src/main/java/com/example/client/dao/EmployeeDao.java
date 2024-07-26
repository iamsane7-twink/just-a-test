package com.example.client.dao;

import com.example.client.entity.Employee;
import com.example.client.entity.Phone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class EmployeeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(value = "tm", propagation = Propagation.REQUIRES_NEW)
    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    @Transactional(value = "tm", propagation = Propagation.REQUIRES_NEW)
    public void attachPhoneToEmployee(Long id, Long phoneId) {
        Employee employee = entityManager.getReference(Employee.class, id);
        Phone phone = entityManager.getReference(Phone.class, phoneId);

        employee.getPhones().add(phone);

        entityManager.persist(employee);
    }
}
