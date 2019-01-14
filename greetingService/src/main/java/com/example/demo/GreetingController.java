package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Greeting microservice", description = "api para microservicio")
public class GreetingController {

	private static final String template = "Hello, %s!";

	@GET
	@RequestMapping("/greeting/{name}")
	@Produces("application/json")
	@ApiOperation(value = "saluda a una persona", notes = "retorna el saludo", produces = "JSON", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })
	public Greeting greeting(@ApiParam(value = "name", required = true) @PathVariable("name") String name) {

		return new Greeting(String.format(template, name));

	}

}
