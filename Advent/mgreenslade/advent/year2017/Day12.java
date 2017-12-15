package advent.year2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import advent.AdventChallenge;
import advent.InputMapper;

public class Day12 extends AdventChallenge
{
	
	private static final InputMapper MAPPER = new InputMapper("(\\d+) <-> ([\\d, ]+)");
	private static final int PIPE = 0;
	private static final int CONNECTIONS = 1;
	
	private Map<String, String[]> pipes = new HashMap<>();
	
	public Day12()
	{
		super("Day 12", "day12.txt.");
		getInputFromFile().forEach((s) ->
		{
			MAPPER.parseInput(s);
			Map<Integer, String> data = MAPPER.getGroupsByPosition();
			String pipe = data.get(PIPE);
			String[] connections = data.get(CONNECTIONS).split(", ");
			pipes.put(pipe, connections);
		});
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		return String.valueOf(findAllConnections("0").size());
	}
	
	private List<String> findAllConnections(String pipe)
	{
		return findAllConnections(pipe, new ArrayList<>());
	}
	
	private List<String> findAllConnections(String pipe, List<String> encountered)
	{
		encountered.add(pipe);
		for (String c : pipes.get(pipe))
		{
			if (!encountered.contains(c))
			{
				findAllConnections(c, encountered);
			}
		}
		return encountered;
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		Set<String> allPipes = new HashSet<>(pipes.keySet());
		int groups = 0;
		while (!allPipes.isEmpty())
		{
			String pipe = allPipes.iterator().next();
			allPipes.removeAll(findAllConnections(pipe));
			groups++;
		}
		return String.valueOf(groups);
	}
	
}
