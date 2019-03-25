package com.scrap.cognito.repository;

import com.scrap.cognito.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
