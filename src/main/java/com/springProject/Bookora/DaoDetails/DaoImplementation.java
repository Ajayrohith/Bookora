package com.springProject.Bookora.DaoDetails;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springProject.Bookora.Entities.Booking;
import com.springProject.Bookora.Entities.Eventdetails;
import com.springProject.Bookora.Entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class DaoImplementation implements DaoInterface{

    private EntityManager entitymanagerObj;

    public DaoImplementation(EntityManager entitymanagerObj)
    {
        this.entitymanagerObj = entitymanagerObj;
    }
     
    @Override
    public User updateuser(User user) {
        User daouserobj = entitymanagerObj.merge(user);
        return daouserobj;
    }

    @Override
    public User finduserbyuserId(int id) {
        return entitymanagerObj.find(User.class, id);
    }

    @Override
    public User findUserbyUsername(String name) {
  
        List<User> tempUserList = entitymanagerObj.createQuery("SELECT u FROM User u WHERE u.userName = :name",User.class).
                            setParameter("name",name).getResultList();
        return tempUserList.isEmpty()? null : tempUserList.get(0);
    }

    @Override
    public String retrieveUserPassword(String susername) {

        try {
        return entitymanagerObj
                .createQuery(
                    "SELECT u.passWord FROM User u WHERE u.userName = :susername",
                    String.class
                )
                .setParameter("susername", susername)
                .getSingleResult();

    } catch (NoResultException e) {
        return null;
    }
    }

    @Override
    public Eventdetails addnewEvent(Eventdetails event) {
        return entitymanagerObj.merge(event);
    }

    @Override
    public Booking createNewbooking(Booking booking) {
        return entitymanagerObj.merge(booking);
    }

    @Override
    public Eventdetails findEventbyId(int eventID) {
        return entitymanagerObj.find(Eventdetails.class, eventID);
    }

    @Override
    public List<User> retriveuserList() {
        
        return entitymanagerObj.createQuery("Select u from User u",User.class).getResultList();
    }

}
