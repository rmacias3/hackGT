import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Hello Unfolding World.
 * 
 * Download the distribution with examples for many more examples and features.
 */
public class HelloUnfoldingWorld extends PApplet {

	UnfoldingMap map;
	List<Marker> transitMarkers;
	// public void setup() {
	// size(800, 600);
	//
	// map = new UnfoldingMap(this);
	// map.zoomAndPanTo(10, new Location(52.5f, 13.4f));
	//
	// MapUtils.createDefaultEventDispatcher(this, map);
	// }
	public void setup() {
		size(800, 600);
		map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		
		transitMarkers = new ArrayList<Marker>();
		 
		// Load features from GeoJSON
//		List<Feature> transitLines = GeoJSONReader.loadData(this, "C:\\Users\\NiNJa\\Desktop\\MBTARapidTransitLines.json");
//		System.out.println(transitLines.size());
//		 
//		// Create markers from features, and use LINE property to color the markers.
//		for (Feature feature : transitLines) {
//		    ShapeFeature lineFeature = (ShapeFeature) feature;
//		 
//		    SimpleLinesMarker m = new SimpleLinesMarker(lineFeature.getLocations());
//		    String lineColor = lineFeature.getStringProperty("LINE");
//		    int color = 0;
//		    // Original MBTA colors
//		    if (lineColor.equals("BLUE")) {
//		        color = color(44, 91, 167);
//		    }
//		    if (lineColor.equals("RED")) {
//		        color = color(233, 57, 35);
//		    }
//		    if (lineColor.equals("GREEN")) {
//		        color = color(59, 130, 79);
//		    }
//		    if (lineColor.equals("SILVER")) {
//		        color = color(154, 156, 157);
//		    }
//		    if (lineColor.equals("ORANGE")) {
//		        color = color(238, 137, 40);
//		    }
//		    m.setColor(color);
//		    m.setStrokeWeight(5);
//		    transitMarkers.add(m);
//		}
//		 
//		map.addMarkers(transitMarkers);
//		
		Location loc1 = new Location(33.7751753, -84.3968956);
		Location loc2 = new Location(33.7951753, -84.3968956);
		SimpleLinesMarker lin = new SimpleLinesMarker(loc1, loc2);
		lin.setStrokeWeight(5);
		int col = color(238, 137, 40);
		lin.setColor(col);
		transitMarkers.add(lin);
		map.addMarker(lin);
		MapUtils.createDefaultEventDispatcher(this, map);
	}

	static int x=0;
	public void draw() {
		background(0);
		map.draw();
		x++;
		if(x==1000){
			Location loc1 = new Location(33.7751753, -84.3868956);
			Location loc2 = new Location(33.7951753, -84.3868956);
			SimpleLinesMarker lin = new SimpleLinesMarker(loc1, loc2);
			lin.setStrokeWeight(5);
			int col = color(238, 137, 40);
			lin.setColor(col);
			map.addMarker(lin);	
		}
		if(x==2000){
			Location loc1 = new Location(33.7551753, -84.3868956);
			Location loc2 = new Location(33.7651753, -84.3868956);
			SimpleLinesMarker lin = new SimpleLinesMarker(loc1, loc2);
			lin.setStrokeWeight(5);
			int col = color(238, 137, 40);
			lin.setColor(col);
			map.addMarker(lin);	
		}
		if(x==2500){
			Location loc1 = new Location(33.7551753, -84.3868956);
			Location loc2 = new Location(33.7951753, -84.3668956);
			SimpleLinesMarker lin = new SimpleLinesMarker(loc1, loc2);
			lin.setStrokeWeight(5);
			int col = color(238, 137, 40);
			lin.setColor(col);
			map.addMarker(lin);	
		}
	}
	// public void setup() {
	// size(800, 600, OPENGL);
	// }
	//
	// public void draw() {
	// }

}
