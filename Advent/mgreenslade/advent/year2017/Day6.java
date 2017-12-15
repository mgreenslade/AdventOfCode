package advent.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.AdventChallenge;

public class Day6 extends AdventChallenge
{
	
	public Day6()
	{
		super("Day 6", "day6.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		int result = computeBankRedistribution(l -> l.size() - 1);
		return String.valueOf(result);
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		int result = computeBankRedistribution(l -> l.size() - l.indexOf(l.get(l.size() - 1)) - 1);
		return String.valueOf(result);
	}
	
	private int computeBankRedistribution(Function<List<Integer>, Integer> getResult)
	{
		List<Integer> banks = getInput();
		List<Integer> states = new ArrayList<>();
		
		for (int state = getHashOfState(banks); !states.contains(state); state = getHashOfState(banks))
		{
			states.add(state);
			int max = Collections.max(banks);
			int i = banks.indexOf(max);
			banks.set(i, 0);
			
			for (int size = banks.size(); max > 0; max--)
			{
				i = (i + 1) % size;
				banks.set(i, banks.get(i) + 1);
			}
		}
		states.add(getHashOfState(banks));
		return getResult.apply(states);
	}
	
	private List<Integer> getInput()
	{
		return Arrays.stream(getInputFromFile().findFirst().get().split("\t")).map(Integer::parseInt).collect(Collectors.toList());
	}
	
	private int getHashOfState(List<Integer> banks)
	{
		return Arrays.hashCode(banks.stream().mapToInt(i -> i).toArray());
	}
}
