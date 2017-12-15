package advent.year2017;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.AdventChallenge;

public class Day9 extends AdventChallenge
{
	private final String INPUT;
	
	public Day9()
	{
		super("Day 9", "day9.txt");
		INPUT = getInputFromFile().findFirst().get();
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		String input = removeGarbage(removeNegatedCharacters(INPUT));
		
		int value = 0;
		int score = 0;
		for (int i : input.chars().toArray())
		{
			char c = (char) i;
			if (c == '{')
			{
				value++;
			}
			else if (c == '}')
			{
				score += value--;
			}
		}
		return String.valueOf(score);
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		int sum = 0;
		Pattern findGarbage = Pattern.compile("<(.*?)>");
		Matcher garbage = findGarbage.matcher(removeNegatedCharacters(INPUT));
		while (garbage.find())
		{
			sum += garbage.group(1).length();
		}
		return String.valueOf(sum);
	}
	
	private String removeGarbage(String string)
	{
		return string.replaceAll("<.*?>", "");
	}
	
	private String removeNegatedCharacters(String string)
	{
		return string.replaceAll("!.", "");
	}
}
