package org.gfg.Library_Management_Minor_Project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.gfg.Library_Management_Minor_Project.Repository.UserRepo;
import org.gfg.Library_Management_Minor_Project.dto.UserRequest;
import org.gfg.Library_Management_Minor_Project.model.Operator;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserFilterType;
import org.gfg.Library_Management_Minor_Project.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public static  final Log logger= LogFactory.getLog("UserService.class");

    @Autowired
    private UserRepo userRepository;
    //whenever we  are creating multiple data resource then to make our data persitent we use persistent context

    @PersistenceContext
    private EntityManager em;

    public User addStudent(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setUserType(UserType.STUDENT);
        return userRepository.save(user);


    }



    public List<User> filter(String filterBy, String operator, String value) {
        String[] filters = filterBy.split(",");
        String[] operators = operator.split(",");
        String[] values=value.split(",");
        StringBuilder query = new StringBuilder();

        query.append("select * from user where ");
        for(int i=0;i<operators.length;i++){
            UserFilterType userFilterType = UserFilterType.valueOf(filters[i]);
            Operator operator1=Operator.valueOf(operators[i]);
            String finalValue=values[i];
            query=query.append(userFilterType).
                    append(operator1.getValue()).
                    append("'").append(finalValue).append("'").append(" and ");
        }
        logger.info("query is :" + query.substring(0,query.length()-4));
        Query query1=em.createNativeQuery(query.substring(0,query.length()-4),User.class);
        return query1.getResultList();
        //return userRepository.findUsersByNativeQuery(query.substring(0,query.length()-4))
    }

    public User getStudentByPhoneNo(String userPhoneNo) {
        return userRepository.findByPhoneNoAndUserType(userPhoneNo,UserType.STUDENT);
    }
}
