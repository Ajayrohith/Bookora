package com.springProject.Bookora.DaoDetails;

import org.springframework.stereotype.Repository;

import com.springProject.Bookora.Entities.User;

import jakarta.persistence.EntityManager;

@Repository
public class DaoImplementation implements DaoInterface{

    private EntityManager entitymanagerObj;

    public DaoImplementation(EntityManager entitymanagerObj)
    {
        this.entitymanagerObj = entitymanagerObj;
    }
     
    @Override
    public User createnewuser(User user) {
        User daouserobj = entitymanagerObj.merge(user);
        return daouserobj;
    }

}
