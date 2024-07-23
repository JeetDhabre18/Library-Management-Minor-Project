package org.gfg.Library_Management_Minor_Project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserRequest {


    private String userName;
    @NotBlank(message = "user phoneNo should not be blank")
    private String phoneNo;

    private String email;

    private String address;
    @NotBlank(message = "user password should not be blank")
    private  String password;



    public User toUser() {
        
        return User.builder().
                Name(this.userName).
                email(this.email).
                phoneNo(this.phoneNo).
                address(this.address).
                password(this.password).
                userStatus(UserStatus.ACTIVE).build();
    }
}
