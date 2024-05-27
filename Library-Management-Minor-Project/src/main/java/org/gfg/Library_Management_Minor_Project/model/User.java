package org.gfg.Library_Management_Minor_Project.model;
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

//it is a class for both student and librarian
public class User extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=30)//by default length is 255
    private String Name;

    @Column(nullable = false ,unique=true,length=13)
    private String phoneNo;

    @Column(unique=true,length=50)
    private  String email;

    private String address;

    @Enumerated(value= EnumType.STRING)
    private UserType userType;

    @Enumerated
    private  UserStatus userStatus;

    @OneToMany(mappedBy = "user") // when you dont want to keep the data into the table
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn>  txnlist;

    private  String temp;
}
// there are  types of relationship
//1.unidirectional and 2.bidirectional(strong)

//you cannot change the constraint once the table is been created but
//you can add column
//for eg for exisiting column you cannot change the constraint in this user table
//you cannot change email nullable =false

// but you can add new column

//you cannot add constraint to an existing table through hibernate

