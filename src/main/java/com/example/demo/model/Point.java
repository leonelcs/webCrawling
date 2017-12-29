package com.example.demo.model;


public class Point {
	
	private Float[] latlng = new Float[2];
	
	private String url;
	
	private String title;
	
	private String subtitle;
	
	private Route route;
	
	private String urlToMaps;
	
	public Float[] getLatlng() {
		return latlng;
	}

	public void setLatlng(Float[] latlng) {
		this.latlng = latlng;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}
	
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public String toString() {
		String result = "Point: {\n";
		result += "   latlng: [" + this.latlng[0]+","+this.latlng[1]+"]\n";
		result += "   url: "+this.url+"\n";
		result += "   title: "+this.title+"\n";
		result += "   subtitle: "+this.subtitle+"\n";
		return result;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getUrlToMaps() {
		return urlToMaps;
	}

	public void setUrlToMaps(String urlToMaps) {
		this.urlToMaps = urlToMaps;
	}

}
