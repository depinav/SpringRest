package com.victord.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.victord.model.Person;
import com.victord.service.PersonService;

@Controller
@RequestMapping("/api")
public class PersonApiController {
	
	@Autowired
	PersonService personService;
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/personlist")
	@ResponseBody
	public String personLists() throws JsonGenerationException, JsonMappingException, IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, personService.listPerson());
		byte[] data = out.toByteArray();
		return new String(data);
	}
	
	@RequestMapping("/person/{id}")
	@ResponseBody
	public String getById(@PathVariable String id) {
		
		String jsonPerson = null;
		
		try {
			jsonPerson = mapper.writeValueAsString(personService.getPerson(id));
		} catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		return jsonPerson;
	}
	
	@RequestMapping("/person/random")
	@ResponseBody
	public String getRandomPerson() {
		
		String jsonPerson = null;
		
		try {
			jsonPerson = mapper.writeValueAsString(personService.getRandomPerson());
		} catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		return jsonPerson;
	}
	
	@RequestMapping(value="/person/save", method=RequestMethod.POST)
	@ResponseBody
	public String savePerson(String name) {
		
		String jsonPerson = null;
		Person person = new Person();
		person.setName(name);
		personService.addPerson(person);
		
		try {
			jsonPerson = mapper.writeValueAsString(personService.getPerson(person.getId()));
		} catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
		
		new RedirectView("/SpringRest/sencha");
		
		return jsonPerson;
	}
	
	@RequestMapping("/person/droplist")
	@ResponseBody
	public String dropPersonList() {
		
		boolean dropped;
		
		dropped = personService.clearCollection();
		
		if(dropped) {
			return "Collection successfully dropped.";
		} else {
			return "Failed to drop list.";
		}
	}
}