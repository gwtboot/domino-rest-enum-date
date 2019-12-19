package com.example.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.PersonDto;
import com.example.api.PersonType;

@Service
public class PersonService {

	public List<PersonDto> getPersons() {
		List<PersonDto> persons = new ArrayList<>();

		PersonDto person1 = new PersonDto();
		person1.setDate(new Date());
		person1.setName("Lofi");
		person1.setPersonType(PersonType.COOL);

		PersonDto person2 = new PersonDto();
		person2.setDate(new Date());
		person2.setName("Kulaki");
		person2.setPersonType(PersonType.BORING);

		persons.add(person2);
		persons.add(person1);

		return persons;
	}
}
