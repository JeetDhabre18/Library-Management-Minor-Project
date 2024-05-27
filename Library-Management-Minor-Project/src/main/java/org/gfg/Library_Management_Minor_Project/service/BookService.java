package org.gfg.Library_Management_Minor_Project.service;

import org.gfg.Library_Management_Minor_Project.Repository.AuthorRepo;
import org.gfg.Library_Management_Minor_Project.Repository.BookRepo;
import org.gfg.Library_Management_Minor_Project.dto.BookRequest;
import org.gfg.Library_Management_Minor_Project.model.Author;
import org.gfg.Library_Management_Minor_Project.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private AuthorRepo authorRepository;

    @Autowired
    private BookRepo bookRepository;
    public Book addBook(BookRequest bookrequest) {
        Author authorfromDb=authorRepository.getAuthorByEmail(bookrequest.getAuthorEmail());

        if(authorfromDb==null){
            //object of author table and try to save the data
            authorfromDb=authorRepository.save(bookrequest.toAuthor());
        }
        Book book=bookrequest.toBook();
        book.setAuthor(authorfromDb);
        return bookRepository.save(book);


    }
}
