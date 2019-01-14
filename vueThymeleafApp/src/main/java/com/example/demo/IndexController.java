package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		Gson g = new Gson();
		JsonArray ar = new JsonArray();
		JsonObject messi = new JsonObject();
		messi.addProperty("id", 1);
		messi.addProperty("name", "Lionel Messi");
		messi.addProperty("description", "Argentina's superstar");
		messi.addProperty("img",
				"https://ichef.bbci.co.uk/news/660/cpsprodpb/DB47/production/_102153165_gettyimages-980598650.jpg");
		ar.add(messi);
		JsonObject cristiano = new JsonObject();
		cristiano.addProperty("id", 2);
		cristiano.addProperty("name", "Christiano Ronaldo");
		cristiano.addProperty("description", "Portugal top-ranked player");
		cristiano.addProperty("img", "https://e.rpp-noticias.io/normal/2019/01/13/571857_738465.jpg");
		ar.add(cristiano);
		cristiano = new JsonObject();
		cristiano.addProperty("id", 3);
		cristiano.addProperty("name", "Andres Iniesta");
		cristiano.addProperty("description", "Experimented Spain player");
		cristiano.addProperty("img",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/ESP-RUS_%2815%29.jpg/245px-ESP-RUS_%2815%29.jpg");
		ar.add(cristiano);
		cristiano = new JsonObject();
		cristiano.addProperty("id", 4);
		cristiano.addProperty("name", "Kylian Mbappe");
		cristiano.addProperty("description", "Young French ranked player");
		cristiano.addProperty("img",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Kylian_Mbapp%C3%A9_Russia_2018.jpg/245px-Kylian_Mbapp%C3%A9_Russia_2018.jpg");
		ar.add(cristiano);
		System.out.println(ar.toString());
		model.addAttribute("eventName", "FIFA 2018").addAttribute("AppName", "Pruebas").addAttribute("data",
				ar.toString());
		return "index";
	}
}
