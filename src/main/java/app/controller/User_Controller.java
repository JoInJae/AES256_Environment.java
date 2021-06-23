package app.controller;

import app.data.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestMapping(value = "/user", method = RequestMethod.POST)
@RestController
public class User_Controller{

    @PersistenceContext
    public EntityManager em;

    @Transactional
    @PostMapping("/insert")
    public void insert(){

        User user = new User();
        em.persist(user);

    }


}
