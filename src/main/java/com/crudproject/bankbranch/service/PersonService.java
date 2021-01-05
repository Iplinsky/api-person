package com.crudproject.bankbranch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudproject.bankbranch.models.Person;
import com.crudproject.bankbranch.repository.IPersonRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PersonService {

	@Autowired
	private IPersonRepository personRepository;

	public Person save(Person obj) {
		return personRepository.save(obj);
	}

	public Person update(Integer id, Person obj) {
		Person newPerson = personRepository.findById(id).orElse(null);
		newPerson.setName(obj.getName());
		newPerson.setEmail(obj.getEmail());
		newPerson.setCpf(obj.getCpf());
		newPerson.setDateBirth(obj.getDateBirth());
		return personRepository.save(newPerson);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person findById(Integer id) throws ObjectNotFoundException {
		Person obj = personRepository.findById(id).orElse(null);
		if (obj == null) {
			throw new ObjectNotFoundException("Object not found!");
		}
		return obj;
	}

	public void delete(Integer id) throws Exception {
		findById(id);
		try {
			personRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("The request could not be completed.");
		}
	}
}