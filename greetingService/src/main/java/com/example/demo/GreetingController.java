package com.example.demo;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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

	@GET
	@RequestMapping("/saludar/{nombre}")
	@Produces("application/json")
	@ApiOperation(value = "saluda a una persona v2", notes = "retorna el saludo", produces = "application/json", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Response.class, responseContainer = "application/json"),
			@ApiResponse(code = 501, message = "Internal server error", response = Response.class, responseContainer = "application/json") })
	public Response saludar(@ApiParam(value = "nombre", required = true) @PathVariable("nombre") String nombre) {
		Random x = new Random();
		Gson gson = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
		if (!x.nextBoolean()) {
			JsonObject error = new JsonObject();
			error.addProperty("codigo", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			error.addProperty("mensaje", "problemas internos comuniquese con el administrador");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
		} else {
			JsonObject ok = new JsonObject();
			ok.addProperty("greeting", String.format(template, nombre));
			return Response.status(Response.Status.OK).entity(StringEscapeUtils.unescapeJava(gson.toJson(ok)))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
