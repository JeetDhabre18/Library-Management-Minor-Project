package org.gfg.Library_Management_Minor_Project.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity

//it is a class for both student and librarian
public class User extends TimeStamp implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=30)//by default length is 255
    private String Name;

    private String password;

    private String authorities;

    @Column(nullable = false ,unique=true,length=13)
    private String phoneNo;

    @Column(unique=true,length=50)
    private  String email;

    private String address;

    @Enumerated(value= EnumType.STRING)
    private UserType userType;

    @Enumerated
    private  UserStatus userStatus;

    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER) // when you dont want to keep the data into the table
    private List<Book> bookList;

    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    @JsonIgnoreProperties(value={"user"})
    private List<Txn>  txnlist;

    private  String temp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] auth=authorities.split(",");
        return  Arrays.stream(auth).map(a->new SimpleGrantedAuthority(a)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
// there are  types of relationship
//1.unidirectional and 2.bidirectional(strong)

//you cannot change the constraint once the table is been created but
//you can add column
//for eg for exisiting column you cannot change the constraint in this user table
//you cannot change email nullable =false

// but you can add new column

//you cannot add constraint to an existing table through hibernate

