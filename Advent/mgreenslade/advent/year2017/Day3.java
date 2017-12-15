package advent.year2017;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import advent.AdventChallenge;

public class Day3 extends AdventChallenge
{
	private static final Point RIGHT = new Point(1, 0);
	private static final Point UP = new Point(0, -1);
	private static final Point LEFT = new Point(-1, 0);
	private static final Point DOWN = new Point(0, 1);
	
	private static final Point UP_LEFT = new Point(-1, -1);
	private static final Point UP_RIGHT = new Point(1, -1);
	private static final Point DOWN_LEFT = new Point(-1, 1);
	private static final Point DOWN_RIGHT = new Point(1, 1);
	
	private static final Point[] ORDER = { RIGHT, UP, LEFT, DOWN };
	private static final Point[] ALL = { RIGHT, UP_RIGHT, UP, UP_LEFT, LEFT, DOWN_LEFT, DOWN, DOWN_RIGHT };
	private Map<Point, Integer> grid = new HashMap<>();
	
	public Day3()
	{
		super("Day 3", "day3.txt");
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		AtomicInteger step = new AtomicInteger(1);
		Point currentPosition = createGrid(point -> step.incrementAndGet());
		
		int currentValue = grid.values().stream().max(Integer::compare).get();
		int numSteps = 0;
		while (currentValue > 1)
		{
			numSteps++;
			int highestDifference = 0;
			Map<Integer, Point> differences = new HashMap<>();
			for (Point d : ORDER)
			{
				Point neighbor = new Point(currentPosition.x + d.x, currentPosition.y + d.y);
				int difference = currentValue - grid.getOrDefault(neighbor, currentValue);
				differences.put(difference, neighbor);
				highestDifference = Math.max(difference, highestDifference);
			}
			currentPosition = differences.get(highestDifference);
			currentValue = grid.get(currentPosition);
		}
		return String.valueOf(numSteps);
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		Point finalPosition = createGrid(this::getSumOfNeighbors);
		return String.valueOf(grid.get(finalPosition));
	}
	
	private Point createGrid(Function<Point, Integer> calculateNextPoint)
	{
		grid.clear();
		int n = 1;
		int stop = Integer.parseInt(getInputFromFile().findFirst().get());
		Point position = new Point(0, 0);
		int direction = 0;
		int steps = 0;
		int maxSteps = 1;
		
		grid.put(new Point(position.x, position.y), n);
		while (n < stop)
		{
			Point translation = ORDER[direction];
			position.translate(translation.x, translation.y);
			if (++steps == maxSteps)
			{
				steps = 0;
				maxSteps += direction % 2;
				direction = (direction + 1) % 4;
			}
			n = calculateNextPoint.apply(position);
			grid.put(new Point(position.x, position.y), n);
		}
		return position;
	}
	
	private int getSumOfNeighbors(Point position)
	{
		int sum = 0;
		for (Point d : ALL)
		{
			Point neighbor = new Point(position.x + d.x, position.y + d.y);
			sum += grid.getOrDefault(neighbor, 0);
		}
		return sum;
	}
	
}
