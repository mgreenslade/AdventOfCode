package advent;

import advent.year2017.*;

public class ChallengeRunner
{
	public static void main(String[] args)
	{
//		runChallenge(new Day1());
//		runChallenge(new Day2());
//		runChallenge(new Day3());
//		runChallenge(new Day4());
//		runChallenge(new Day5());
//		runChallenge(new Day6());
//		runChallenge(new Day7());
//		runChallenge(new Day8());
//		runChallenge(new Day9());
//		runChallenge(new Day10());
//		runChallenge(new Day11());
//		runChallenge(new Day12());
//		runChallenge(new Day13());
//		runChallenge(new Day14());
	}
	
	static void runChallenge(AdventChallenge challenge)
	{
		System.out.println(challenge.getName() + " Part 1: " + challenge.solveFirstPuzzle());
		System.out.println(challenge.getName() + " Part 2: " + challenge.solveSecondPuzzle());
		System.out.println();
	}
}
