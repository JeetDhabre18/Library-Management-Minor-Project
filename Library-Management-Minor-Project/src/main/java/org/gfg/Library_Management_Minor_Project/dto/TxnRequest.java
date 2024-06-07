package org.gfg.Library_Management_Minor_Project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TxnRequest {
    @NotBlank(message = "User phoneNo should not be blank")
    private String userPhoneNo;
    @NotBlank(message = "BookNo should not be blank")
    private String bookNo;
}
