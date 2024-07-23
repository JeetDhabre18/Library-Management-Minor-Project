package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {


    @Query (value = "select * from user where :q",nativeQuery = true)
List<User> findByNativeQuery(String q);
    User findByPhoneNoAndUserType(String phoneNo, UserType type);
    User findByEmail(String email);
}
