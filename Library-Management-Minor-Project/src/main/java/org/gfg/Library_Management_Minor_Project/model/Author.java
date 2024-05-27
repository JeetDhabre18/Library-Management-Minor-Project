package org.gfg.Library_Management_Minor_Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Author extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "author")
    //@JsonIgnore //it will help to remove the cycle or a loop between author and book
    @JsonIgnoreProperties(value="author") // to remove cycle and also to get the list of book
    List<Book> bookList;

    @Column(length=30)//by default length is 255
    private String Name;

    @Column(unique=true,nullable = false, length=50)
    private  String email;
}
