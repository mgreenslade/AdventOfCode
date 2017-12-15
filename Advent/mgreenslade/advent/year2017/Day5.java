package advent.year2017;

import java.util.function.Function;

import advent.AdventChallenge;

public class Day5 extends AdventChallenge
{
	
	public Day5()
	{
		super("Day 5", "day5.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		return String.valueOf(countSteps(i->i+1));
	}

	@Override
	public String solveSecondPuzzle()
	{
		return String.valueOf(countSteps(i->i>=3?i-1:i+1));
	}
	
	private int countSteps(Function<Integer, Integer> change)
	{
		int[] ints = getInputFromFile().mapToInt(Integer::parseInt).toArray();
		int steps = 0;
		for (int i = 0; i < ints.length; steps++)
		{
			int n = i;
			i += ints[i];
			ints[n] = change.apply(ints[n]);
		}
		return steps;
	}
	
}
