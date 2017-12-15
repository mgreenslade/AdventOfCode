package advent.year2017;

import java.util.Arrays;
import java.util.stream.IntStream;

import advent.AdventChallenge;

public class Day14 extends AdventChallenge
{
	
	public Day14()
	{
		super("Day 14", "day14.txt");
	}
	
	public int[][] createMapOfDisk(String input)
	{
		String[] hashes = new String[128];
		for (int i = 0; i < 128; i++)
		{
			String line = input + "-" + i;
			hashes[i] = Day10.createDenseHash(line.chars());
		}
		
		int[][] usedSpaces = new int[128][128];
		for (int r = 0; r < hashes.length; r++)
		{
			String[] chars = hashes[r].split("");
			for (int c = 0; c < chars.length; c++)
			{
				int hexValue = Integer.parseInt(chars[c], 16);
				for (int i = 0; i < 4; i++)
				{
					usedSpaces[r][(c * 4) + i] = (hexValue >> (3-i)) & 1;
				}
			}
		}
		return usedSpaces;
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		int[][] usedSpaces = createMapOfDisk(getSingleLineInput());
		return String.valueOf(Arrays.stream(usedSpaces).mapToInt(i -> Arrays.stream(i).sum()).sum());
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		int[][] usedSpaces = createMapOfDisk(getSingleLineInput());
		int count = 0;
		for (int r = 0; r < usedSpaces.length; r++)
		{
			for (int c = 0; c < usedSpaces[r].length; c++)
			{
				if (usedSpaces[r][c] == 1)
				{
					floodFill(usedSpaces, r, c, count++ + 2);
				}
			}
		}
		
		return String.valueOf(count);
	}
	
	private void floodFill(int[][] grid, int r, int c, int mark)
	{
		if (grid[r][c] == 1)
		{
			grid[r][c] = mark;
			if (r > 0)
			{
				floodFill(grid, r - 1, c, mark);
			}
			if (r < grid.length - 1)
			{
				floodFill(grid, r + 1, c, mark);
			}
			if (c > 0)
			{
				floodFill(grid, r, c - 1, mark);
			}
			if (c < grid[r].length - 1)
			{
				floodFill(grid, r, c + 1, mark);
			}
		}
	}
	
	private String visualizeGrid(int[][] spaces)
	{
		String pad = "%4d ";
		String lines = "\n";
		for (int r = 0; r < spaces.length; r++)
		{
			String line = "| ";
			for (int c = 0; c < spaces[r].length; c++)
			{
				line += String.format(pad, spaces[r][c]);
			}
			lines += line + "\n";
		}
		return lines;
	}
}
