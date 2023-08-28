package com.recipespoint.backend.dal.repository;

import com.recipespoint.backend.dal.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, String> {}