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
package com.example.client;

import java.util.Date;
import java.util.logging.Logger;

import org.dominokit.domino.rest.DominoRestConfig;

import com.example.api.ErrorDto;
import com.example.api.PersonDto;
import com.example.api.PersonType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class BasicGwtEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(BasicGwtEntryPoint.class.getName());

	@Override
	public void onModuleLoad() {
		DominoRestConfig.initDefaults();

		PersonDto coolPerson = new PersonDto();
		coolPerson.setDate(new Date());
		coolPerson.setName("Lofi");
		coolPerson.setPersonType(PersonType.COOL);

		ErrorDto boringError = new ErrorDto();
		boringError.setDetail(new Date().toString());
		boringError.setErrorcode("Test");
		boringError.setStatus(PersonType.BORING.toString());

		FlowPanel flowPanel = new FlowPanel();

		Button personListButton = executePersonList(coolPerson);
		Button personWithErrorListButton = executePersonWithErrorList(boringError);

		flowPanel.add(personListButton);
		flowPanel.add(personWithErrorListButton);

		RootPanel.get("flowPanel").add(flowPanel);
	}

	private Button executePersonList(PersonDto person) {
		Button personListButton = new Button("Click me: " + person.getPersonType().name());

		personListButton.addClickHandler(clickEvent -> {
			logger.info("Hello World: executePersonList");

			PersonClientFactory.INSTANCE.getPersons().onSuccess(response -> {
				response.forEach(p -> logger
						.info("Person: " + p.getName() + " - Date: " + p.getDate() + " - Type: " + p.getPersonType()));
			}).onFailed(failedResponse -> {
				logger.info(
						"Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
			}).send();
		});

		return personListButton;
	}

	private Button executePersonWithErrorList(ErrorDto error) {
		Button personWithErrorListButton = new Button("Click me: " + error.getDetail());

		personWithErrorListButton.addClickHandler(clickEvent -> {
			logger.info("Hello World: executePersonWithErrorList");

			PersonClientFactory.INSTANCE.getPersonsWithError().onSuccess(response -> {
				response.forEach(e -> logger.info("Error Code: " + e.getErrorcode()));
			}).onFailed(failedResponse -> {
				logger.info(
						"Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
			}).send();

		});

		return personWithErrorListButton;
	}

}