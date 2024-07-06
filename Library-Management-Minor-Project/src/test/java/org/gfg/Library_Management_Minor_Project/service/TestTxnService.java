package org.gfg.Library_Management_Minor_Project.service;

import org.gfg.Library_Management_Minor_Project.Exception.TxnException;
import org.gfg.Library_Management_Minor_Project.Repository.TxnRepo;
import org.gfg.Library_Management_Minor_Project.dto.TxnRequest;
import org.gfg.Library_Management_Minor_Project.model.Book;
import org.gfg.Library_Management_Minor_Project.model.Txn;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.junit.Assert;
import  org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestTxnService {
    @InjectMocks
    private TxnService txnService;

    @Mock
    private UserService userService ;

    @Mock
    private BookService bookService ;

    @Mock
    private TxnRepo txnRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        //txnService = new TxnService();
        ReflectionTestUtils.setField(txnService,"validDays",12);
        ReflectionTestUtils.setField(txnService,"fineAmountPerDay",1);

    }

    @Test
    public void testCalculateFine() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-01");
        Txn txn= Txn.builder().createdOn(date)
                .build();
        int calFine=txnService.calculateFine(txn,100);
        Assert.assertEquals(48,calFine);
    }


    @Test(expected = TxnException.class)
    public void testGetUserFromDb() throws TxnException {
        TxnRequest txnRequest=TxnRequest.builder().build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(null);
        txnService.getUserFromDB(txnRequest);

    }

    @Test
    public void testGetUserFromDbWhenNoException() throws TxnException {
        TxnRequest txnRequest=TxnRequest.builder().build();
        User user= User.builder().id(1).build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(user);
        User output=txnService.getUserFromDB(txnRequest);
        Assert.assertEquals(user.getId(),output.getId());


    }

    @Test
    public void testReturnBook() throws TxnException, ParseException {
        TxnRequest txnRequest=TxnRequest.builder().build();

        User user= User.builder().id(1).build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(user);
        List<Book> list =new ArrayList<>();
        list.add(Book.builder().id(1).bookNo("1").user(user).Securityamt(100).build());
        when(bookService.filter(any(),any(),any())).thenReturn(list);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-01");
        Txn txn=Txn.builder().id(1).user(user).book(list.get(0)).createdOn(date).build();
        when(txnRepository.findByUserPhoneNoAndBookBookNoAndTxnStatus(any(),any(),any())).thenReturn(txn);
        int fine=txnService.returnBook(txnRequest);
        Assert.assertEquals(48,fine);

    }



}
