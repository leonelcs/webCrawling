package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Point;
import com.example.demo.model.Route;
import com.google.gson.Gson;

@Service
public class WebCrawlingServiceImpl implements com.example.demo.services.WebCrawlingService {
	
	String baseUrl = "https://www.expatrentals.eu/country/netherlands/amsterdam";
	String baseQuery = "country_id=1&locationNameText=Amsterdam&area_id=1&streetName=Center&limitToStreet=False&";
	String key = "AIzaSyDLimi0c-T2pOM0PTOyKzM01J_7eUoe0oM";
	
	Float[] destination = new Float[2];
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Override
	public List<Point> getPoints(Float latitude, Float longitude, Integer distance, Integer minimalPrice, Integer maxPrice, boolean furnished, Integer minBedRooms, Integer maxBedRooms) {
		String query = String.format("latitude=%s&longitude=%s&distance=%s&min_price_rent=%s&max_price_rent=%s&furnished=%s&bedrooms=%s&max_bedrooms=%s&",
								latitude.toString(),
								longitude.toString(),
								distance.toString(),
								minimalPrice.toString(),
								maxPrice.toString(),
								furnished?"True":"False",
								minBedRooms.toString(), 
								maxBedRooms.toString());
        String url = 
	            baseUrl+"?"+baseQuery+query+"min_date=&order=1";
        System.out.println(url);
        List<Point> pointList = new ArrayList<>();
        try { 
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("script");
            Element element = elements.get(elements.size()-1);
            String data = element.data();
            String[] dataLines = data.split("\n");
            List<String> dataList = Arrays.asList(dataLines);
            List<String> points = new ArrayList<>();
            dataList.forEach(dl -> {
            	if (dl.trim().startsWith("points.push("))
            		points.add(dl.trim());
            } );
            List<String> newPoints = points.stream().map(  
            	p ->  p.substring(12, p.length()).substring(0, p.length()-14)
             ).collect(Collectors.toList());
             
            newPoints.stream().forEach(
            		p -> { 
            			Gson gson = new Gson();
            			Point point = gson.fromJson(p, Point.class);
            			pointList.add(point);
            		});
            pointList.forEach(p -> {
    			p.setRoute(getDistancesByPoint(p));
    			p.setUrlToMaps(createMapsLink(p.getLatlng()));
    		});
            return pointList;

        } catch (Exception ex) { 
            // Handle exceptions 
        }
        return pointList;
	}

	@Override
	public Route getDistancesByPoint(Point point) {
		
		destination[0] = 52.3233781f;
		destination[1] = 4.9509263f;

		String urlMaps = "https://maps.googleapis.com/maps/api/distancematrix/json?";
		String mode = "&mode=transit";
		
		this.restTemplate = restTemplate();

		Gson gson = new Gson();
		Route route = gson.fromJson(restTemplate.getForObject(urlMaps+"origins="+point.getLatlng()[0].toString()+","+point.getLatlng()[1].toString()+"&destinations="+this.destination[0].toString()+","+this.destination[1].toString()+mode+"&key="+key, String.class),Route.class);
		return route;
	}

	@Override
	public String createMapsLink(Float[] origin) {
		
		this.destination[0] = 52.3233781f;
		this.destination[1] = 4.9509263f;
		String baseURL = "https://www.google.com.br/maps/dir/";
		String url = baseURL + "'" + origin[0].toString() + "," + origin[1].toString()
				+ "'/'" + destination[0]+","+destination[1]+"'";
		
		return url;
	}


}
