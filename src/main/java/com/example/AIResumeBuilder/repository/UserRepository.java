package com.example.AIResumeBuilder.repository;

import com.example.AIResumeBuilder.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}