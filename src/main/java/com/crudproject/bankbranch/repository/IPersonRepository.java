package com.crudproject.bankbranch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudproject.bankbranch.models.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
}

