package org.gfg.Library_Management_Minor_Project.Repository;


import org.gfg.Library_Management_Minor_Project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserCacheRepository {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static String userKey="user::";
    public User getUser(String email){
       String key=userKey+email;
        return (User) redisTemplate.opsForValue().get(key);
    }

    public void setUser(String email,User user){
        String key=userKey+email;
         redisTemplate.opsForValue().set(key,user,10, TimeUnit.MINUTES);
    }
}
