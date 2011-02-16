package gis2.scenario;

import gis2.Destination;
import gis2.RandomScenarioGenerator;
import gis2.Scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rescuecore2.worldmodel.EntityID;
import maps.gml.GMLShape;

public class RandomiseDestination extends AbstractFunction {

	private Random random;
	
	public RandomiseDestination(ScenarioEditor editor) {
		 super(editor);
	        random = new Random();
	}

	@Override
	public String getName() {
		return "Randomise Destination for Civilians";
	}

	@Override
	public void execute() {
		ArrayList<Destination>destinations=editor.getScenario().getDestination();
		List<GMLShape> all = new ArrayList<GMLShape>(editor.getMap().getAllShapes());
		Random random=new Random();
		for(int i=0;i<destinations.size();i++)
		{
			Destination d=destinations.get(i);
			d.getEnds().clear();			
			int des=all.get(random.nextInt(all.size())).getID();
			d.getEnds().add(des);
		}	
	}
}