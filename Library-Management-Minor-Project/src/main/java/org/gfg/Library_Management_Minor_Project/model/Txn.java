package org.gfg.Library_Management_Minor_Project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Txn {
        //extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String txnId ;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


    @ManyToOne
    @JoinColumn
    private User user;

    private int SettlementAmount;

    @ManyToOne
    @JoinColumn
    private  Book book;

    @Enumerated
    private TxnStatus txnStatus;


}
