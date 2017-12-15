package advent.year2017;

import java.util.Arrays;
import java.util.stream.IntStream;

import advent.AdventChallenge;

public class Day10 extends AdventChallenge
{
	public Day10()
	{
		super("Day 10", "day10.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		int[] initialRope = IntStream.range(0, 256).toArray();
		int[] lengths = Arrays.stream(getInputFromFile().findFirst().get().split(",")).mapToInt(i -> Integer.parseInt(i)).toArray();
		int[] hashedRope = performKnotHash(initialRope, lengths, 1);
		return String.valueOf(hashedRope[0] * hashedRope[1]);
	}
	
	public static int[] performKnotHash(int[] initialRope, int[] lengths, int numTimes)
	{
		return performKnotHash(initialRope, lengths, 0, 0, numTimes);
	}

	private static int[] performKnotHash(int[] rope, int[] lengths, int skip, int position, int numTimes)
	{
		int ropeLength = rope.length;
		for (int length : lengths)
		{
			int[] subsection = new int[length];
			for (int i = position, n = 0; n < length; n++)
			{
				subsection[n] = rope[i];
				i = (i + 1) % ropeLength;
			}
			for (int i = position, n = length - 1; n >= 0; n--)
			{
				rope[i] = subsection[n];
				i = (i + 1) % ropeLength;
			}
			position = (position + length + skip++) % ropeLength;
		}
		if (--numTimes > 0)
		{
			return performKnotHash(rope, lengths, skip, position, numTimes);
		}
		else
		{
			return rope;
		}
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		return createDenseHash(getInputFromFile().findFirst().get().chars());
	}

	public static String createDenseHash(IntStream chars)
	{
		int[] initialRope = IntStream.range(0, 256).toArray();
		
		int[] lengths = IntStream.concat(chars, IntStream.of(17, 31, 73, 47, 23)).toArray();
		int[] hashedRope = performKnotHash(initialRope, lengths, 64);
		String denseHash = "";
		for (int position = 0; position < 256; position += 16)
		{
			int XOR = hashedRope[position];
			for (int i = position + 1; i < position + 16; i++)
			{
				XOR ^= hashedRope[i];
			}
			denseHash += String.format("%1$02x", XOR);
		}
		return denseHash;
	}
	
}
