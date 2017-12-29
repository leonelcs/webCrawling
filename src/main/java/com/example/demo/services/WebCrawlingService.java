package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Point;
import com.example.demo.model.Route;

public interface WebCrawlingService {
	
	List<Point> getPoints(Float latitude, Float longitude, Integer distance, Integer minimalPrice, Integer maxPrice, boolean furnished, Integer minBedRooms, Integer maxBedRooms);
	
	Route getDistancesByPoint(Point point);
	
	String createMapsLink(Float[] origin);
	
}
