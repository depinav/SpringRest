package com.victord.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.victord.model.Person;

@Repository
public class PersonService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
     
    public static final String COLLECTION_NAME = "person";
     
    public void addPerson(Person person) {
        if (!mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.createCollection(Person.class);
        }       
        person.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(person, COLLECTION_NAME);
    }
     
    public List<Person> listPerson() {
        return mongoTemplate.findAll(Person.class, COLLECTION_NAME);
    }
     
    public void deletePerson(Person person) {
        mongoTemplate.remove(person, COLLECTION_NAME);
    }
     
    public void updatePerson(Person person) {
        mongoTemplate.insert(person, COLLECTION_NAME);      
    }
    
    public Person getPerson(String id) {
    	
    	return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);
    }
    
    public Person getRandomPerson() {
    	
    	List<Person> listOPpl = mongoTemplate.findAll(Person.class, COLLECTION_NAME);
    	Person person = null;
    	
    	if(!listOPpl.isEmpty()) {
    		person = listOPpl.get(0);
    	}
    	
    	return person;
    }
    
    public boolean clearCollection() {
    	
    	if (mongoTemplate.collectionExists(COLLECTION_NAME)) {
    		
    		mongoTemplate.dropCollection(COLLECTION_NAME);
    		return true;
    	} else
    		return false;
    }
}