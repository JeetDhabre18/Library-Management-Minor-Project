package org.gfg.Library_Management_Minor_Project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.gfg.Library_Management_Minor_Project.model.Author;
import org.gfg.Library_Management_Minor_Project.model.Book;
import org.gfg.Library_Management_Minor_Project.model.BookType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookRequest {


    @NotBlank(message = "Book title should not be blank")
    private String title;
     @NotBlank(message = "Book no should not be blank")
    private String bookNo;
     @NotBlank(message = "Author name should not be blank")
    private String authorName;
     @NotBlank(message = "Author Email should not be blank")
    private String authorEmail;
     @NotNull(message = "Book type  should not be blank")
    private BookType type;
    @Positive(message = "Security amount should be positive")
    private int securityAmount;


    public Author toAuthor() {
        return Author.builder().
                email(this.authorEmail).
                Name(this.authorName).
                build();
    }

    public Book toBook() {
        return Book.builder().
                title(this.title).
                BookNo(this.bookNo).
                Securityamt(this.securityAmount).
                bookType(this.type).
                build();
    }
}
