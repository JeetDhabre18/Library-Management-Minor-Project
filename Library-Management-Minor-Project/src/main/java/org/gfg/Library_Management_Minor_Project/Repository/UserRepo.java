package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
}
