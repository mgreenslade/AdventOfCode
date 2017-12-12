package advent.year2017;

import advent.AdventChallenge;

public class Day1 extends AdventChallenge
{
	
	public Day1()
	{
		super("day1.txt");
	}
	
	/**
	 * Retrieve the sum of all numbers in a list that match the number one space ahead of them
	 */
	@Override
	public String solveFirstPuzzle()
	{
		int[] ints = formatInput();
		return String.valueOf(sumList(ints, 1));
	}
	
	/**
	 * Retrieve the sum of all numbers in a list that match the number (list.length/2) spaces ahead of them
	 */
	@Override
	public String solveSecondPuzzle()
	{
		int[] ints = formatInput();
		return String.valueOf(sumList(ints, ints.length / 2));
	}
	
	private int[] formatInput()
	{
		String[] line = getInputFromFile().findFirst().get().split("");
		int length = line.length;
		int[] ints = new int[length];
		
		for (int i = 0; i < length; i++)
		{
			ints[i] = Integer.valueOf(line[i]);
		}
		return ints;
	}
	
	private int sumList(int[] ints, int step)
	{
		int sum = 0;
		int length = ints.length;
		
		for (int i = 0; i < length; i++)
		{
			if (ints[(i + step) % length] == ints[i])
			{
				sum += ints[i];
			}
		}
		return sum;
	}
}
