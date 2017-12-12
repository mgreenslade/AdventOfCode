package advent.year2017;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import advent.AdventChallenge;

public class Day2 extends AdventChallenge
{
	
	public Day2()
	{
		super("Day 2", "day2.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		Integer result = reduceInputsWithFunction((carry, array) -> carry + array[array.length - 1] - array[0]);
		return String.valueOf(result);
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		return String.valueOf(reduceInputsWithFunction((carry, array) ->
		{
			for (int x : array)
			{
				for (int y : array)
				{
					if (x != y && x % y == 0)
					{
						carry += x / y;
					}
				}
			}
			return carry;
		}));
	}
	
	private Integer reduceInputsWithFunction(BiFunction<Integer, ? super int[], Integer> reductionFunction)
	{
		return getInputAsSortedArrays().reduce(0, reductionFunction, Integer::sum);
	}
	
	private Stream<int[]> getInputAsSortedArrays()
	{
		return getInputFromFile().map(line -> Arrays.stream(line.split("\\t")).mapToInt(Integer::parseInt).sorted().toArray());
	}
}
