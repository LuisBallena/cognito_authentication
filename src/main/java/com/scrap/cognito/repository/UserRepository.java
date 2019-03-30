package com.scrap.cognito.repository;

import com.scrap.cognito.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRepository.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
