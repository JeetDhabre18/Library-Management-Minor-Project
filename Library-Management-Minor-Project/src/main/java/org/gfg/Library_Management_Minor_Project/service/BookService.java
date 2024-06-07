package org.gfg.Library_Management_Minor_Project.service;

import org.gfg.Library_Management_Minor_Project.Repository.AuthorRepo;
import org.gfg.Library_Management_Minor_Project.Repository.BookRepo;
import org.gfg.Library_Management_Minor_Project.dto.BookRequest;
import org.gfg.Library_Management_Minor_Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // for one value
    public List<Book> filter(BookFilterType bookFilterType, Operator operator, String value) {
        switch (bookFilterType){
            case BOOK_TITLE :
                switch (operator){
                    case EQUALS :
                        return bookRepository.findByTitle(value);
                    case LIKE:
                        return bookRepository.findByTitleContaining(value);
                    default:
                        new ArrayList<Book>();
                }
            case BOOK_TYPE:
                switch (operator) {
                    case EQUALS:
                        return bookRepository.findByBookType(BookType.valueOf(value));

                }
            case BOOK_NO:
                switch (operator) {
                    case EQUALS:
                        return bookRepository.findByBookNo(value);
                }
            default: new ArrayList<Book>();
        }
        return new ArrayList<>();
    }

    public void updateBookData(Book bookFromDB) {
        bookRepository.save(bookFromDB);
    }
}
