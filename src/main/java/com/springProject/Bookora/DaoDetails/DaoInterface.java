package com.springProject.Bookora.DaoDetails;

import com.springProject.Bookora.Entities.User;

public interface DaoInterface {

    public User updateuser(User user);

    public User findUserbyUsername(String name);

    public String retrieveUserPassword(String username);

}
