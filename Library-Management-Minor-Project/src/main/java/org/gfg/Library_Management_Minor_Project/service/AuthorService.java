package org.gfg.Library_Management_Minor_Project.service;

import org.gfg.Library_Management_Minor_Project.Repository.AuthorRepo;
import org.gfg.Library_Management_Minor_Project.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepository;

    public Author getAuthorData(String email) {
        return authorRepository.getAuthorByEmail(email);
    }
}
