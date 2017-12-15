package advent.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import advent.AdventChallenge;
import advent.InputMapper;

public class Day7 extends AdventChallenge
{
	private static final InputMapper MAPPER = new InputMapper("(\\w+) \\((\\d+)\\)(?: -> ([\\w, ]+))?");
	private static final int NAME = 0;
	private static final int WEIGHT = 1;
	private static final int CARRYING = 2;
	private static final String SEPERATOR = ", ";
	
	public Day7()
	{
		super("Day 7", "day7.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		Map<String, Program> tower = buildTower();
		return getBottomProgram(tower).name;
	}
	
	private Program getBottomProgram(Map<String, Program> tower)
	{
		Collection<Program> programs = tower.values();
		for (Program program : programs)
		{
			if (programs.stream().filter((p) -> p.heldPrograms.contains(program)).count() == 0)
			{
				return program;
			}
		}
		return null;
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		Map<String, Program> programs = buildTower();
		Program program = getBottomProgram(programs);
		return String.valueOf(findWeightNeededToBalance(program, 0));
	}
	
	private int findWeightNeededToBalance(Program program, int expectedWeight)
	{
		if (program.heldPrograms.isEmpty()
			|| program.heldPrograms.stream().mapToInt(p -> p.getTotalWeight()).distinct().count() == 1)
		{
			return program.weight - (program.getTotalWeight() - expectedWeight);
		}
		else
		{
			List<Integer> weights = Arrays.asList(program.heldPrograms.stream().map(p -> p.getTotalWeight()).toArray(Integer[]::new));
			Program unbalancedProgram = program.heldPrograms.stream().filter(p -> Collections.frequency(weights, p.getTotalWeight()) == 1).findFirst().get();
			int correctWeight = weights.stream().distinct().filter(i -> !i.equals(unbalancedProgram.getTotalWeight())).findFirst().get();
			return findWeightNeededToBalance(unbalancedProgram, correctWeight);
		}
	}
	
	private Map<String, Program> buildTower()
	{
		Map<String, Program> programs = new HashMap<>();
		getInputFromFile().forEach((line) ->
		{
			MAPPER.parseInput(line);
			Map<Integer, String> data = MAPPER.getGroupsByPosition();
			String name = data.get(NAME);
			programs.putIfAbsent(name, new Program());
			
			Program p = programs.get(name);
			p.name = name;
			p.weight = Integer.parseInt(data.get(WEIGHT));
			String carrying = data.get(CARRYING);
			if (carrying != null)
			{
				for (String carryName : carrying.split(SEPERATOR))
				{
					programs.putIfAbsent(carryName, new Program());
					p.heldPrograms.add(programs.get(carryName));
				}
			}
		});
		return programs;
	}
	
	private class Program
	{
		String name;
		int weight;
		List<Program> heldPrograms = new ArrayList<>();
		private int totalWeight = 0;
		
		public Integer getTotalWeight()
		{
			if (totalWeight == 0)
			{
				totalWeight = weight + heldPrograms.stream().mapToInt(p -> p.getTotalWeight()).sum();
			}
			return totalWeight;
		}
	}
}
