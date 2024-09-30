package com.amdocs.project.vaccination_management.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amdocs.project.vaccination_management.entity.Person;

import jakarta.persistence.EntityManager;

@Repository
public class PersonDao {
	
	private EntityManager personManager;
	
	public PersonDao(EntityManager thePersonManager) {
		this.personManager=thePersonManager;
	}
	
	ArrayList<Person> persons = new ArrayList<Person>();
	
	@Transactional
	public ArrayList<Person> getPerson(){
		Session currentSession= personManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("from Person",Person.class);
		persons= (ArrayList<Person>) theQuery.getResultList();
		
		return persons;
	}
	

	@Transactional
	public Person getPerson(int personId) {
	    Session currentSession = personManager.unwrap(Session.class);

	    // Fetching a single record by ID
	    Person person = currentSession.get(Person.class, personId);

	    // If no Person is found with the given personId, throw an exception
	    if (person == null) {
	        throw new RuntimeException("Person with ID " + personId + " not found");
	    }

	    return person;
	}
	
	


	@Transactional
	public Person save(Person thePerson) {
	    // Validate that Aadhaar number is exactly 12 digits
	    if (String.valueOf(thePerson.getAadhar_num()).length() != 12) {
	        throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
	    }

	    // Validate that Phone number is exactly 10 digits
	    if (String.valueOf(thePerson.getPhone_num()).length() != 10) {
	        throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
	    }

	    // Validate that dose2 is only set if dose1 is not null
	    if (thePerson.getDose2() != null && thePerson.getDose1() == null) {
	        throw new RuntimeException("Dose2 cannot be entered as Dose1 is null.");
	    }
	    // Validate that dose1 and dose2 are not null
	    if (thePerson.getBooster_dose() != null && (thePerson.getDose1() == null || thePerson.getDose2() == null)) {
	        throw new RuntimeException("Booster dose cannot be entered unless both Dose1 and Dose2 are taken.");
	    }

	    // Update the status based on the doses
	    thePerson.updateStatus();

	    // Save or update the person entity
	    Session currentSession = personManager.unwrap(Session.class);
	    currentSession.saveOrUpdate(thePerson);

	    return thePerson;
	}

	@Transactional
	public List<Person> getPersonsByStatus(String status) {
	    Session currentSession = personManager.unwrap(Session.class);

	    // Create a query to get persons based on their vaccination status
	    Query<Person> query = currentSession.createQuery("from Person where status = :status", Person.class);
	    query.setParameter("status", status);

	    // Execute the query and get the result list
	    List<Person> persons = query.getResultList();

	    return persons;
	}

		
	
//	@Transactional
//	public Person save(Person thePerson) {
//		Session currentSession = personManager.unwrap(Session.class);
//		// Validate that dose2 is only set if dose1 is not null
//	    if (thePerson.getDose2() != null && thePerson.getDose1() == null) {
//	        throw new RuntimeException("Dose2 cannot be entered as Dose1 is null.");
//	    }
//
//	    // Update the status based on the doses
//	    thePerson.updateStatus();
//
//		currentSession.saveOrUpdate(thePerson);
//		
//		return thePerson;
//	}
	
	@Transactional
	public void deletePerson(int personId) {
	    Session currentSession = personManager.unwrap(Session.class);
	    Person person = currentSession.get(Person.class, personId);
	    
	    // Check if the person exists and if both doses are not null
	    if (person == null) {
	        throw new RuntimeException("Person with ID " + personId + " not found");
	    }

	    if (person.getDose1() == null || person.getDose2() == null) {
	        throw new RuntimeException("Cannot delete. Both doses must be administered.");
	    }

	    currentSession.delete(person);
	}

	
}
