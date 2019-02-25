package com.aurigaspa.gateway.api.utils;

public class OverpassApiConstants {
	public final static String SAMPLE_URL = "http://overpass-api.de/api/interpreter?data=%5Bout%3Ajson%5D%3Barea%5B%22ISO3166-1%22%3D%22AL%22%5D%5Badmin_level%3D2%5D%3B%28node%5B%22place%22%3D%22town%22%5D%28area%29%3B%29%3Bout%3B";
	
	public final static String BASE_URL = "http://overpass-api.de/api/interpreter?data=";
	public final static String OUTPUT_FORMAT = "[out:json];";

	//object types
	public final static String OBJECT_TYPE_RELATION = "relation";
	public final static String OBJECT_TYPE_WAY = "way";
 	public final static String OBJECT_TYPE_NODE = "node";
 	public final static String OBJECT_TYPE_AREA = "area";
 	
			
	//Tag conditions
	public final static String TAG_TYPE_BOUNDARY = "[\"type\"=\"boundary\"]";
	public final static String TAG_ADMINISTRATIVE_BOUNDARY = "[\"boundary\"=\"administrative\"]";
	public final static String TAG_ADMIN_LEVEL_2 = "[\"admin_level\"=\"2\"]";
	
	public final static String TAG_PLACE_TOWN_OR_CITY = "[\"place\"~\"town|city\"](area)";

	// Converting output
	public final static String CONVERT_OUTPUT_FOR_COUNTRY = "convert country ::id = id(), name = t[\"name:en\"];";

	// Output
	public final static String OUTPUT_STATEMENT = "out;";
	
	public final static String OUTPUT_TAG_STATEMENT = "out center tags;";
}
