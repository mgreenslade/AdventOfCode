package advent.year2017;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import advent.AdventChallenge;
import advent.InputMapper;

public class Day8 extends AdventChallenge
{
	public Day8()
	{
		super("Day 8", "day8.txt");
		fillRegisters();
	}
	
	final static InputMapper MAPPER = new InputMapper("(\\w+) (\\w+) ([-]?\\d+) if (\\w+) ([<>=!][=]?) ([-]?\\d+)");
	
	final static int OUTPUT_REGISTER = 0;
	final static int OUTPUT_OP = 1;
	final static int OUTPUT_AMOUNT = 2;
	final static int CONDITION_REGISTER = 3;
	final static int CONDITION_OP = 4;
	final static int CONDITION_AMOUNT = 5;
	
	static int highest = 0;
	final static Map<String, BiFunction<Integer, Integer, Boolean>> conditionOp = new HashMap<>();
	static
	{
		conditionOp.put("==", (a, b) -> a.equals(b));
		conditionOp.put("!=", (a, b) -> !a.equals(b));
		conditionOp.put(">", (a, b) -> a > b);
		conditionOp.put("<", (a, b) -> a < b);
		conditionOp.put(">=", (a, b) -> a >= b);
		conditionOp.put("<=", (a, b) -> a <= b);
	}
	
	final static Map<String, BiFunction<Integer, Integer, Integer>> outputOp = new HashMap<>();
	static
	{
		
		outputOp.put("inc", (a, b) -> a + b);
		outputOp.put("dec", (a, b) -> a - b);
	}
	
	final Map<String, Integer> registers = new HashMap<>();
	
	public void fillRegisters()
	{
		getInputFromFile().forEachOrdered((input) ->
		{
			MAPPER.parseInput(input);
			computeInput(MAPPER.getGroupsByPosition());
		});
	}
	
	private void computeInput(Map<Integer, String> data)
	{
		
		int check = getRegister(data.get(CONDITION_REGISTER));
		int against = Integer.valueOf(data.get(CONDITION_AMOUNT));
		
		if (conditionOp.get(data.get(CONDITION_OP)).apply(check, against))
		{
			String outputRegister = data.get(OUTPUT_REGISTER);
			int output = getRegister(outputRegister);
			int toAdd = Integer.valueOf(data.get(OUTPUT_AMOUNT));
			
			int afterOp = outputOp.get(data.get(OUTPUT_OP)).apply(output, toAdd);
			registers.put(outputRegister, afterOp);
			
			if (highest < afterOp)
			{
				highest = afterOp;
			}
		}
	}
	
	private int getRegister(String conditionRegister)
	{
		registers.putIfAbsent(conditionRegister, 0);
		return registers.get(conditionRegister);
	}
	
	@Override
	public String solveFirstPuzzle()
	{
		return String.valueOf(Collections.max(registers.values()));
	}
	
	@Override
	public String solveSecondPuzzle()
	{
		return String.valueOf(highest);
	}
}
