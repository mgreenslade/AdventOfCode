package advent;

import advent.year2017.*;

public class ChallengeRunner
{
	public static void main(String[] args)
	{
		runChallenge(new Day1());
		runChallenge(new Day2());
	}
	
	static void runChallenge(AdventChallenge challenge)
	{
		System.out.println(challenge.getName() + " Part 1: " + challenge.solveFirstPuzzle());
		System.out.println(challenge.getName() + " Part 2: " + challenge.solveSecondPuzzle());
	}
}
