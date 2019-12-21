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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.ErrorDto;
import com.example.api.PersonApi;
import com.example.api.PersonDto;
import com.example.api.PersonEndpoint;
import com.example.api.PersonException;

@CrossOrigin
@RestController
public class PersonController implements PersonApi {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = PersonEndpoint.PERSON_LIST)
	public List<PersonDto> getPersons() {
		logger.info("Controller: getPersons");

		List<PersonDto> persons = personService.getPersons();

		return persons;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = PersonEndpoint.PERSON_WITH_ERROR_LIST)
	public List<ErrorDto> getPersonsWithError() throws PersonException {
		logger.info("Controller: getPersonsWithError");
		try {
			throw new PersonException("Cannot access the file");
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", exc);
		}
	}

}
