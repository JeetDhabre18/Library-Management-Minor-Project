package org.gfg.Library_Management_Minor_Project.service;

import jakarta.transaction.Transactional;
import org.gfg.Library_Management_Minor_Project.Exception.TxnException;

import org.gfg.Library_Management_Minor_Project.Repository.TxnRepo;
import org.gfg.Library_Management_Minor_Project.dto.TxnRequest;
import org.gfg.Library_Management_Minor_Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private UserService userService ;

    @Autowired
    private BookService bookService ;

    @Autowired
    private TxnRepo txnRepository;

    //for collecting data from application properties
    @Value("${book.valid.upto}")
    private int validDays;

    @Value("${book.fine.amount.per.day}")
    private int fineAmountPerDay;

     public User getUserFromDB(TxnRequest txnRequest) throws TxnException {
         // student is valid or not?
         User userFromDB=userService.getStudentByPhoneNo(txnRequest.getUserPhoneNo());
         if(userFromDB == null){
             throw new TxnException("Student does not belong to my library");
         }
         return userFromDB;
     }

     public Book getBookFromDB(TxnRequest txnRequest) throws TxnException {
         List<Book> books=bookService.filter(BookFilterType.BOOK_NO, Operator.EQUALS, txnRequest.getBookNo());
         if(books.isEmpty()){
             throw new TxnException("Book does not exist");
         }
         Book bookFromDB=books.get(0);
         return bookFromDB;
     }

    @Transactional(rollbackOn = {TxnException.class})
     public String createTransaction(User userFromDB, Book bookFromDB)  {
         String txnId= UUID.randomUUID().toString();
         Txn txn=Txn.builder().
                 txnId(txnId).
                 user(userFromDB).
                 book(bookFromDB).
                 txnStatus(TxnStatus.ISSUED).build();
         txnRepository.save(txn);
         //setting the user is book table
         bookFromDB.setUser(userFromDB);
         bookService.updateBookData(bookFromDB);

         return txnId;

     }
    //by default transactional only work for run time exception
    // and we are using compile time exception
    public String create(TxnRequest txnRequest) throws TxnException {
        User userFromDB=getUserFromDB(txnRequest);
        Book bookFromDB=getBookFromDB(txnRequest);
        if(bookFromDB.getUser()!=null){
            throw new TxnException("book already issued to someone else");
        }
        return  createTransaction(userFromDB,bookFromDB);

    }


    @Transactional(rollbackOn = {TxnException.class})
    public int returnBook(TxnRequest txnRequest) throws TxnException {
         User userFromDB=getUserFromDB(txnRequest);
         Book bookFromDB=getBookFromDB(txnRequest);
         if(bookFromDB.getUser()!=userFromDB){
             throw new TxnException("this is not the user which the book was assigned to");
         }
         Txn txn= txnRepository.findByUserPhoneNoAndBookBookNoAndTxnStatus(txnRequest.getUserPhoneNo(),
                 txnRequest.getBookNo(),TxnStatus.ISSUED);
         int fine =calculateFine(txn,bookFromDB.getSecurityamt());
         if(fine == bookFromDB.getSecurityamt()){
             txn.setTxnStatus(TxnStatus.RETURNED);
         }else {
             txn.setTxnStatus(TxnStatus.FINED);
         }
          txn.setSettlementAmount(fine);
          bookFromDB.setUser(null);
          bookService.updateBookData(bookFromDB);
          return fine;
    }

    private int calculateFine(Txn txn,int securityamt) {
         long issueDate=txn.getCreatedOn().getTime();
         long returnDate=System.currentTimeMillis();
         long timeDiff=returnDate - issueDate;
         int dayPass= (int) TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);
         if(dayPass>validDays){
             int fineAmount =(dayPass-validDays)*fineAmountPerDay;
             return securityamt-fineAmount;
         }
         return securityamt;
    }
}
