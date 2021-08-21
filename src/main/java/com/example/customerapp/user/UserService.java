package com.example.customerapp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();

    }

    public void save(User user) {
        repo.save(user);
    }
    public void delete(Integer id) throws UserPrincipalNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count ==0){
            throw new UserPrincipalNotFoundException("Couldn't found customer" + id);
        }
        repo.deleteById(id);
    }
}
