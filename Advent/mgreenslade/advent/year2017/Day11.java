package advent.year2017;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import advent.AdventChallenge;

public class Day11 extends AdventChallenge
{
	private Map<String, CubeCoordinate> DIRECTIONS = new HashMap<>();
	{
		DIRECTIONS.put("n", new CubeCoordinate(0, 1, -1));
		DIRECTIONS.put("s", new CubeCoordinate(0, -1, 1));
		DIRECTIONS.put("ne", new CubeCoordinate(1, 0, -1));
		DIRECTIONS.put("sw", new CubeCoordinate(-1, 0, 1));
		DIRECTIONS.put("se", new CubeCoordinate(1, -1, 0));
		DIRECTIONS.put("nw", new CubeCoordinate(-1, 1, 0));
	}
	
	public Day11()
	{
		super("Day 11", "day11.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		String[] directions = getInputFromFile().findFirst().get().trim().split(",");
		CubeCoordinate c = new CubeCoordinate(0, 0, 0);
		for (String direction : directions)
		{
			c.translate(DIRECTIONS.get(direction));
		}
		
		return String.valueOf(c.getDistance());
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		String[] directions = getInputFromFile().findFirst().get().trim().split(",");
		CubeCoordinate c = new CubeCoordinate(0, 0, 0);
		int highest = 0;
		for (String direction : directions)
		{
			c.translate(DIRECTIONS.get(direction));
			highest = Math.max(highest, c.getDistance());
		}
		
		return String.valueOf(highest);
	}
	
	class CubeCoordinate
	{
		int x;
		int y;
		int z;
		
		CubeCoordinate(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		void translate(CubeCoordinate c)
		{
			x += c.x;
			y += c.y;
			z += c.z;
		}
		
		int getDistance()
		{
			return Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));
		}
	}
}
