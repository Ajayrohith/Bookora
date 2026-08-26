package com.springProject.Bookora.ServiceDetails;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springProject.Bookora.DaoDetails.DaoInterface;
import com.springProject.Bookora.Entities.Apiresponse;
import com.springProject.Bookora.Entities.User;
import com.springProject.Bookora.Entities.Userloginrequest;

import jakarta.transaction.Transactional;

@Service
public class Servicelayer {

    private DaoInterface daoobject;

    public Servicelayer(DaoInterface daoobject)
    {
        this.daoobject = daoobject;
    }

    @Transactional
    public User createNewUser(User sUser)
    {
        User existinguser = daoobject.findUserbyUsername(sUser.getUserName());
        if(existinguser != null)
        {
            throw new RuntimeException("User already exists");
        }
        else{
             return daoobject.updateuser(sUser);
        }
       
    }

    public boolean validateUser(Userloginrequest suser)
    {
        String inputPassword = suser.getPassWord();
        String passWordfromDb = daoobject.retrieveUserPassword(suser.getUserName());


        if(passWordfromDb == null)
        {
            return false;
        }
        return inputPassword.equals(passWordfromDb);
    }

}
