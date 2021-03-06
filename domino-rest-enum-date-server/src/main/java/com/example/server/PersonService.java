/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.api.PersonDto;
import com.example.api.PersonType;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	public List<PersonDto> getPersons() {
		logger.info("Service: getPersons");

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
