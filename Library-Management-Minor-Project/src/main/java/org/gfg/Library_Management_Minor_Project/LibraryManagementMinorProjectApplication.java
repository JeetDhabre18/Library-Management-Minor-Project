package org.gfg.Library_Management_Minor_Project;

import org.gfg.Library_Management_Minor_Project.Repository.BookRepo;
import org.gfg.Library_Management_Minor_Project.Repository.TxnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementMinorProjectApplication implements CommandLineRunner {
    @Autowired
	//private BookRepo bookRepository;
	private TxnRepo txnRepository;


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementMinorProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
         txnRepository.updateExisitingTransaction();
		 System.out.println("My Application has started");
	}
}
//commnadline runner will