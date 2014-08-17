package com.knesek.springmockedtests.repository;

import com.knesek.springmockedtests.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author knesek
 * Created on: 17/08/14
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
