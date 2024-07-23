package org.gfg.Library_Management_Minor_Project.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Book extends TimeStamp  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=30)//by default length is 255
    private String title;

    //identifier
    @Column(length=20,unique=true)
    private String bookNo;

    @Enumerated(value= EnumType.STRING)
    private BookType bookType;


    private Integer Securityamt;

    @ManyToOne
    @JoinColumn
    private  User user;


    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;



}
//relationship between tables

// it there is many to many relationship than we hava to create another table
// it there is one to many or many to one then we will use as a foreign key
// one student can have many books or one book will be assigned by many student

//Author to book one to many
// one author can write many book
//one book can be written by one author


//in one transaction user can take only one book
//one book can have many transaction

//there is a creation of cycle between author and book due to bidirectional relationship
//to ignore this cycle we should use @JsonIgnore