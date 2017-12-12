package advent;

import advent.year2017.Day1;

public class ChallengeRunner
{
	public static void main(String[] args)
	{
		runChallenge(new Day1());
	}
	
	static void runChallenge(AdventChallenge challenge) {
		System.out.println(challenge.solveFirstPuzzle());
		System.out.println(challenge.solveSecondPuzzle());
	}
}
