package com.lexicon.repository;

import com.lexicon.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Sergiy_Dakhniy
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByFbId(String fbId);
    List<User> getAllExceptOne(@Param("excludedUser") String excludedUser);
}
