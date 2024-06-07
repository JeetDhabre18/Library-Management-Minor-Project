package org.gfg.Library_Management_Minor_Project.controller;

import jakarta.validation.Valid;
import org.gfg.Library_Management_Minor_Project.dto.BookRequest;
import org.gfg.Library_Management_Minor_Project.model.Book;
import org.gfg.Library_Management_Minor_Project.model.BookFilterType;
import org.gfg.Library_Management_Minor_Project.model.Operator;
import org.gfg.Library_Management_Minor_Project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    // for retrieving book by using filter
    @GetMapping("/filter")
    public List<Book> filter(@RequestParam("bookFilterType") BookFilterType bookFilterType,
                @RequestParam("operator") Operator operator, @RequestParam("value") String value) {
        return bookService.filter(bookFilterType,operator,value);
    }
}
//creating or insert book in book table(row)
//u  have to insert the information related to author who has written this
//we have to check whether the author is present or not dont add author if present


// dto-data transfer  object

