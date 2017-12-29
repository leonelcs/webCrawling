package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Point;
import com.example.demo.model.Route;
import com.example.demo.services.WebCrawlingService;

@Controller
public class WebCrawlingController {
	
	@Autowired
	WebCrawlingService service;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
//	@GetMapping("/points")
//	public List<Point> getPoints() {
//		Float latitude = 52.3233781f;
//		Float longitude = 4.9509263f;
//		Integer distance = 20000;
//		Integer minimalPrice = 1000;
//		Integer maxPrice = 1500;
//		boolean furnished = true;
//		Integer minBedRooms = 1;
//		Integer maxBedRooms = 3;
//		Float[] destination = new Float[2];
//		
//		destination[0] = 52.3233781f;
//		destination[1] = 4.9509263f;
//		
//		List<Point> listPoints = service.getPoints(latitude, longitude, distance, minimalPrice, maxPrice, furnished, minBedRooms, maxBedRooms);
//		listPoints.forEach(p -> {
//			p.setRoute(service.getDistancesByPoint(p));
//		});
//		
//		return listPoints;
//	}

}
