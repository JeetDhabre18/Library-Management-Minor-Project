package org.gfg.Library_Management_Minor_Project.Repository;

import org.gfg.Library_Management_Minor_Project.model.Txn;
import org.gfg.Library_Management_Minor_Project.model.TxnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepo extends JpaRepository<Txn,Integer> {

        Txn findByUserPhoneNoAndBookBookNoAndTxnStatus(String userPhoneNo, String bookNo, TxnStatus txnStatus);
}
