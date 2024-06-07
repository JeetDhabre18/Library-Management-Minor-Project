package org.gfg.Library_Management_Minor_Project.service;

import org.gfg.Library_Management_Minor_Project.Repository.UserRepo;
import org.gfg.Library_Management_Minor_Project.dto.UserRequest;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;
    public User addStudent(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setUserType(UserType.STUDENT);
        return userRepository.save(user);


    }



//    public List<User> filter(String filterBy, String operator, String value) {
//        String[] filters = filterBy.split(",");
//        String[] operators = operator.split(",");
//        String[] values=value.split(",");
//    }

    public User getStudentByPhoneNo(String userPhoneNo) {
        return userRepository.findByPhoneNoAndUserType(userPhoneNo,UserType.STUDENT);
    }
}
