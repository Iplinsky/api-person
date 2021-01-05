package com.crudproject.bankbranch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudproject.bankbranch.models.Person;
import com.crudproject.bankbranch.service.PersonService;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<Person> personSave(@RequestBody Person person) {
		personService.save(person);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Person> personUpdate(@PathVariable Integer id, @RequestBody Person newPerson) {
		Person personObj;
		try {
			personObj = personService.update(id, newPerson);
			return new ResponseEntity<Person>(personObj, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Optional<Person>> personDelete(@PathVariable Integer id) {
		try {
			personService.delete(id);
			return new ResponseEntity<Optional<Person>>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<Person>> personFindAll() {
		List<Person> list = personService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> personFindById(@PathVariable Integer id) {
		Person newPerson;
		try {
			newPerson = personService.findById(id);
			return new ResponseEntity<Person>(newPerson, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
}