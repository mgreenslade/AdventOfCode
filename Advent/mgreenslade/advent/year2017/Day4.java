package advent.year2017;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import advent.AdventChallenge;

public class Day4 extends AdventChallenge
{
	
	public Day4()
	{
		super("Day 4", "day4.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		return parseInput(c -> c);
	}
	
	private String parseInput(Function<String, String> formattingFunction)
	{
		return String.valueOf(getInputFromFile().map(s -> !Arrays.stream(s.split(" ")).collect(Collectors.groupingBy(formattingFunction, Collectors.counting())).values().stream().anyMatch(l -> l > 1)).reduce(0, (
			carry, item) -> carry + (item ? 1 : 0), Integer::sum));
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		return parseInput(c ->
		{
			String[] split = c.split("");
			Arrays.sort(split, (a, b) -> a.compareTo(b));
			return String.join("", split);
		});
	}
}
