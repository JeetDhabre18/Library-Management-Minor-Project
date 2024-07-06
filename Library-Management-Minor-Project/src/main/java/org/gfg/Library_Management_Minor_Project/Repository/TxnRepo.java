package org.gfg.Library_Management_Minor_Project.Repository;

import jakarta.transaction.Transactional;
import org.gfg.Library_Management_Minor_Project.model.Txn;
import org.gfg.Library_Management_Minor_Project.model.TxnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TxnRepo extends JpaRepository<Txn,Integer> {

        Txn findByUserPhoneNoAndBookBookNoAndTxnStatus(String userPhoneNo, String bookNo, TxnStatus txnStatus);
        @Transactional
        @Modifying
        @Query(value ="update txn set created_on = '2024-05-01 17:11:07.756000' , settlement_amount=0 ,  txn_status=0  where id =1 ", nativeQuery = true)
        void updateExisitingTransaction();
}
