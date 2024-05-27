package org.gfg.Library_Management_Minor_Project.controller;

import jakarta.validation.Valid;
import org.gfg.Library_Management_Minor_Project.dto.BookRequest;
import org.gfg.Library_Management_Minor_Project.model.Book;
import org.gfg.Library_Management_Minor_Project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody @Valid BookRequest bookrequest) {
        //1.validation

        //2.to call the business logic
        Book book=bookService.addBook(bookrequest);

        //3.return the accurate required data
        return book;
    }
}
//creating or insert book in book table(row)
//u  have to insert the information related to author who has written this
//we have to check whether the author is present or not dont add author if present


// dto-data transfer  object

