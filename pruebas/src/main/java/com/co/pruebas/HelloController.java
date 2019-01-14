package com.co.pruebas;

import lombok.Data;
import lombok.experimental.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	String hello() {
		return "Hello World!";
	}

	@Data
	static class Result {
		private final long left;
		private final long right;
		private final long answer;
	}

	// SQL sample
	@RequestMapping("calc")
	Result calc(@RequestParam long left, @RequestParam long right) {
		MapSqlParameterSource source = new MapSqlParameterSource().addValue("left", left).addValue("right", right);
		return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
				(rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
	}

	/* prueba future */
	@RequestMapping("test")
	Result test() {
		Date uno = new Date();
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 100; i++) {
			Future<String> future = procesar();
			list.add(future);
		}
		Integer total = 0;
		for (Future<String> obj : list) {
			try {
				total += Integer.parseInt(obj.get());
			} catch (Exception e) {

			}
		}
		Date dos = new Date();
		System.out.println(dos);
		System.out.println(uno);
		System.out.println((dos.getTime() - uno.getTime()) / 1000);
		System.out.println(total);
		return new Result(uno.getTime(), dos.getTime(), Long.parseLong(total.toString()) * 100 / 1000);
	}

	ExecutorService executor = Executors.newFixedThreadPool(25);

	public Future<String> procesar() {
		Future<String> future = executor.submit(() -> {
			Random x = new Random();
			Integer i = x.nextInt(5) + 5;
			System.out.println("--->" + i * 100 + "--" + Thread.currentThread().getName() + "--" + (new Date()));
			Thread.sleep(1000);
			return i.toString();
		});

		return future;
	}
}
