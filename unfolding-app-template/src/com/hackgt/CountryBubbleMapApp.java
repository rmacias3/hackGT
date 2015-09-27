package com.hackgt;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Shows data bubbles for each country. Loads country shapes, and calculates the
 * centroid to create point marker.
 * 
 * This example loads polygon features (countries) and displays a single data
 * marker (population) in their center.
 * 
 * Uses the data CSV for all countries from
 * http://opengeocode.org/download/cow.php
 */
public class CountryBubbleMapApp extends PApplet {

	UnfoldingMap map;
        static CountryBubbleMapApp INSTANCE;

	HashMap<String, DataEntry> dataEntriesMap;
	List<Marker> countryMarkers;
        String lat[];
        String lon[];

        @SuppressWarnings("empty-statement")
    public CountryBubbleMapApp() {
        
    }

	public void setup() {
		System.out.println("start");
		size(1000, 600);
//		smooth();
                INSTANCE = this;
                
                lat = new String[]{"33.774151", "33.7739563", "33.7759899", "33.7459899", "33.7469899", "33.7569899", "33.7685748", "33.7635748"};
                lon = new String[]{"-84.396424","-84.3878713","-84.3863809","-84.3963809","-84.3933809","-84.3833809","-84.3931313","-84.3961313"};

		map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		map.zoomToLevel(2);
		map.setBackgroundColor(240);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		try{
		Scanner sc = new Scanner(new File("C:\\Users\\NiNJa\\Downloads\\data.csv"));
		int i = 0;
		double[] val = new double[8];
		while(sc.hasNext()){
			String line = sc.nextLine();
			line = line.trim();
			val[i] = Double.parseDouble(line);
			i++;
		}
		loadData(val);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
        
        synchronized public void loadData(double soln[]) {
            // Load country polygons and adds them as markers
		try {
			
			for(int i=0;i<lat.length;i++){
				
				Location loc = new Location(Double.parseDouble(lat[i]), Double.parseDouble(lon[i]));
				SimplePointMarker marker = new SimplePointMarker(loc);
				float radius = (float) (1.0 * soln[i]);
//				float s = map((float) radius, 0, 20000, 0, 100);
				marker.setRadius((float) radius * radius * radius);
				map.addMarkers(marker);
				map.zoomAndPanTo(11, loc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        }
                

	public void draw() {
		//background(240);

		// Draw map tiles and country markers
		map.draw();
		
	}

	public HashMap<String, DataEntry> loadPopulationFromCSV(String fileName) {
		HashMap<String, DataEntry> dataEntriesMap = new HashMap<String, DataEntry>();

		String[] rows = loadStrings(fileName);
		int i = 0;
		for (String row : rows) {
			i++;
			if (i < 30) {
				continue;
			}
			// Reads country name and population density value from CSV row
			String[] columns = row.split(";");
			if (columns.length >= 52) {
				DataEntry dataEntry = new DataEntry();
				dataEntry.countryName = columns[4]; // country name
				dataEntry.id = columns[1].trim(); // 3 letter ISO code
				dataEntry.value = Float.parseFloat(columns[52]); // population
				dataEntriesMap.put(dataEntry.id, dataEntry);
			}
		}

		return dataEntriesMap;
	}

	public class DataEntry {
		String countryName;
		String id;
		Integer year;
		Float value;
	}

}
