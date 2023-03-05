package com.spring.spring_security.service;

import com.spring.spring_security.dao.SubscriberRepo;
import com.spring.spring_security.model.Subscriber;
import com.spring.spring_security.model.SubscriberSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private SubscriberRepo subscriberRepo;

    @Autowired
    public CustomUserDetailsService(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Subscriber> subscribers = subscriberRepo.findByEmail(username);
        if(subscribers.isEmpty()){
            throw  new UsernameNotFoundException("User Email not exist " + username);
        }
        return new SubscriberSecurity(subscribers.get(0));
    }
}

