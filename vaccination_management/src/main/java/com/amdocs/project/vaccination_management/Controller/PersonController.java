package com.amdocs.project.vaccination_management.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amdocs.project.vaccination_management.dao.PersonDao;
import com.amdocs.project.vaccination_management.entity.Person;


@RestController
@RequestMapping("/api")
public class PersonController {
	@Autowired
	
	private PersonDao personDao;

	
	public PersonController(PersonDao thePersonDao) 
	{
		this.personDao= thePersonDao;
	}
	
	@GetMapping("/persons")
	public ArrayList<Person> getPerson()
	{
		ArrayList<Person> persons = personDao.getPerson();
		return persons;
	}
	@GetMapping("/persons/{personId}")
	public ResponseEntity<?> getPerson(@PathVariable int personId) {
	    try {
	        Person person = personDao.getPerson(personId);
	        return new ResponseEntity<>(person, HttpStatus.OK);
	    }
	    	catch (RuntimeException e) {
	        // Handle the exception and return an appropriate response
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    
	    	}
	    
	}
	

	
	@PostMapping("/persons")
	public ResponseEntity<?> addPerson(@Validated @RequestBody Person thePerson) {
	    thePerson.setId(0);
	    Person person = personDao.save(thePerson);
	    return new ResponseEntity<>(person, HttpStatus.CREATED);
	}

	@PutMapping("/persons")
	public ResponseEntity<?> updatePerson(@Validated @RequestBody Person thePerson) {
	    // The same logic as for adding
	    thePerson.updateStatus(); // Make sure to update status before saving
	    Person person = personDao.save(thePerson);
	    return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@DeleteMapping("/persons/{personId}")
	public ResponseEntity<String> deletePerson(@PathVariable int personId) {
	    try {
	        personDao.deletePerson(personId);
	        return new ResponseEntity<>("Person with ID " + personId + " deleted successfully.", HttpStatus.OK);
	    } catch (RuntimeException e) {
	        // Handle the exception and return an appropriate response
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	@GetMapping("/persons/status/{status}")
	public List<Person> getPersonsByStatus(@PathVariable String status) {
	    return personDao.getPersonsByStatus(status);
	}



}
