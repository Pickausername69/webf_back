package com.example.springboot.sqlconnection;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.sqlconnection.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}