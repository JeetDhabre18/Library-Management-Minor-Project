package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.Book;
import org.gfg.Library_Management_Minor_Project.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String title);

    List<Book> findByBookType(BookType bookType);


    List<Book> findByBookNo(String bookNo);
}
