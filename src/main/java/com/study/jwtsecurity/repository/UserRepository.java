package com.study.jwtsecurity.repository;

import com.study.jwtsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepository가 들고있음.
//@Repository라는 어노테이션이 없어도 IoC가 된다. 이유는 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
