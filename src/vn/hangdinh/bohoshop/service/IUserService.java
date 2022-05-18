package vn.hangdinh.bohoshop.service;

import vn.hangdinh.bohoshop.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User loginAdmin(String username,String password) ;


    void add(User newUser);
    void update(User newUser);
    boolean exist(int id);
    boolean checkDuplicateEmail(String email);
    boolean checkDuplicatePhone(String phone);
    boolean checkDuplicateUserName(String userName);
    boolean checkDuplicateId(int id);
    User getUserById (int id);
    boolean existById(int id);


}
