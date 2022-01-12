package com.example.demo.repository;

import com.example.demo.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    List<Parent> findByFirstName(String firstName);

}
