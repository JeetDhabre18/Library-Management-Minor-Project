package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    //native query
    @Query(value = "select * from Author where email= :email",nativeQuery = true)
    Author getAuthorByEmail(String email);
}
//3 ways to write the queries
