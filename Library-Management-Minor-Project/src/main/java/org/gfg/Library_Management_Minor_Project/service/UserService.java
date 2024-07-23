package org.gfg.Library_Management_Minor_Project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.gfg.Library_Management_Minor_Project.Exception.UserException;
import org.gfg.Library_Management_Minor_Project.Repository.UserCacheRepository;
import org.gfg.Library_Management_Minor_Project.Repository.UserRepo;
import org.gfg.Library_Management_Minor_Project.dto.UserRequest;
import org.gfg.Library_Management_Minor_Project.model.Operator;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.model.UserFilterType;
import org.gfg.Library_Management_Minor_Project.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    public static  final Log logger= LogFactory.getLog("UserService.class");


    @Autowired
    private UserCacheRepository cacheRepository;

    @Autowired
    private UserRepo userRepository;
    //whenever we  are creating multiple data resource then to make our data persitent we use persistent context

    @PersistenceContext
    private EntityManager em;

    @Value("${student.authority}")
    private String studentAuthority;

    @Value("${admin.authority}")
    private String adminAuthority;

    @Autowired
    private PasswordEncoder encoder;

    public User addStudent(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setAuthorities(studentAuthority);
        user.setPassword(encoder.encode(userRequest.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //load this user from redis first if present then get the data

        // if not present in redis i want to go to db and then add to redis
        User user = cacheRepository.getUser(email);
        if(user != null){
            return user;
        }
        user =  userRepository.findByEmail(email);
        if(user==null){
            try {
                throw new UserException("the user does not belong to this library");
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }
        cacheRepository.setUser(email,user);
        return user;
    }

    public User addAdmin(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setAuthorities(adminAuthority);
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setUserType(UserType.ADMIN);
        return userRepository.save(user);
    }
}
