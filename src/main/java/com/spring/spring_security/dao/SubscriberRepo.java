package com.spring.spring_security.dao;


import java.util.List;

import com.spring.spring_security.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber,Long>{
    List<Subscriber> findByEmail(String email);
}
