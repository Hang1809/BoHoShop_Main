package vn.hangdinh.bohoshop.service;
import vn.hangdinh.bohoshop.model.Role;
import vn.hangdinh.bohoshop.model.User;
import vn.hangdinh.bohoshop.utils.CSVUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static String path = "data/users.csv";
    @Override
    public List<User> getUsers() {
       List<User> newUsers = new ArrayList<>();
       List<String> records = CSVUtils.readData(path);
       for (String record : records){
           if (!record.trim().isEmpty())
           newUsers.add(User.parseUser(record));
       }
       return newUsers;
    }

    @Override
    public User loginAdmin(String username,String password) {
        List <User> users = getUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)
                    && user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
        }



    @Override
    public void add(User newUser) {
        List<User> users = getUsers();
        users.add(newUser);
        CSVUtils.writeData(path, users);
    }

    @Override
    public void update(User newUser) {
        List <User> users = getUsers();
        for (User user : users ){
            if (user.getId() == newUser.getId()){
                if (newUser.getFullName() != null && !newUser.getFullName().isEmpty())
                    user.setFullName(newUser.getFullName());
                if (newUser.getPhone() != null && !newUser.getPhone().isEmpty())
                    user.setPhone(newUser.getPhone());
                if (newUser.getAddress() != null && !newUser.getAddress().isEmpty())
                    user.setAddress(newUser.getAddress());
                if (newUser.getEmail() != null && !newUser.getEmail().isEmpty())
                    user.setEmail(newUser.getEmail());
                if (newUser.getUserName() != null && !newUser.getUserName().isEmpty())
                    user.setUserName(newUser.getUserName());
                if (newUser.getPassword() != null && !newUser.getPassword().isEmpty())
                    user.setPassword(newUser.getPassword());
                CSVUtils.writeData(path, users);
                break;
            }
        };
    }

    @Override
    public boolean exist(int id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        List <User> users = getUsers();
        for (User user : users){
            if (user.getEmail().equals(email))
                return true ;
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        List<User> users = getUsers();
        for (User user : users){
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        List <User> users = getUsers();
        for (User user : users){
            if (user.getUserName().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        List <User> users = getUsers();
        for (User user : users){
            if (user.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        List <User> users = getUsers();
        for (User user : users ){
            if (id == user.getId())
                return user;
        }
        return null;
    }
    public boolean existById(int id){
        return getUserById(id) != null;
    }

}
