package com.example.demo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Greeting implements Serializable {

	public Greeting(String format) {
		this.content = format;
	}

	private String content;
}
