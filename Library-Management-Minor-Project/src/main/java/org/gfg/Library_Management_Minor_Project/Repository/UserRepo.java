package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {

    User findByPhoneNoAndUserType(String phoneNo, UserType type);
}
