package org.gfg.Library_Management_Minor_Project.controller;

import jakarta.validation.Valid;
import org.gfg.Library_Management_Minor_Project.Exception.TxnException;
import org.gfg.Library_Management_Minor_Project.dto.TxnRequest;
import org.gfg.Library_Management_Minor_Project.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TxnController {
    @Autowired
    private TxnService txnService;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid TxnRequest txnRequest) throws TxnException {
        String txnId = txnService.create(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }

    //update for fineamount in table
    @PutMapping("/return")
    public ResponseEntity<Integer> returnBook(@RequestBody @Valid TxnRequest txnRequest) throws TxnException {
        int txnId = txnService.returnBook(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }
}
