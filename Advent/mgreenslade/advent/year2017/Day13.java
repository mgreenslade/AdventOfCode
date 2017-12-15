package advent.year2017;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import advent.AdventChallenge;

public class Day13 extends AdventChallenge
{
	
	private Map<Integer, Integer> layers = new HashMap<>();
	private int size;
	
	public Day13()
	{
		super("Day 13", "day13.txt");
		createFirewall();
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		return String.valueOf(calculateSeverity(0));
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		int delay = 0;
		int check = (layers.get(0) - 1) * 2;
		while (calculateSeverity(delay) != 0 || delay % check == 0)
		{
			delay++;
		}
		return String.valueOf(delay);
	}
	
	private int calculateSeverity(int delay)
	{
		int severity = 0;
		
		for (int i = 0; i <= size; i++)
		{
			if (layers.containsKey(i))
			{
				int check = (i + delay) % ((layers.get(i) - 1) * 2);
				if (check == 0)
				{
					severity += layers.get(i) * i;
				}
			}
		}
		
		return severity;
	}
	
	private void createFirewall()
	{
		getInputFromFile().forEach((layer) ->
		{
			String data[] = layer.split(":");
			int depth = Integer.parseInt(data[0]);
			int range = Integer.parseInt(data[1].trim());
			layers.put(depth, range);
		});
		size = Collections.max(layers.keySet());
	}
}
