package advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMapper
{
	private static Pattern groupNamePattern = Pattern.compile("\\(\\?<(\\w+)>.*?\\)");
	
	private Pattern inputPattern;
	private List<String> groupNames = new ArrayList<>();
	private final List<String> capturedGroups = new ArrayList<>();
	private final Map<String, String> capturedNamedGroups = new HashMap<>();
	
	public InputMapper(Pattern inputPattern)
	{
		this.inputPattern = inputPattern;
		populateGroupNames(inputPattern);
	}
	
	public InputMapper(String inputPattern)
	{
		this(Pattern.compile(inputPattern));
	}
	
	private void populateGroupNames(Pattern inputPattern)
	{
		Matcher groupNameMatcher = groupNamePattern.matcher(inputPattern.pattern());
		while (groupNameMatcher.find()) {
			groupNames.add(groupNameMatcher.group(1));
		}
	}
	
	public boolean parseInput(String input)
	{
		capturedGroups.clear();
		capturedNamedGroups.clear();
		
		Matcher parsedInput = inputPattern.matcher(input);
		if (!parsedInput.find())
		{
			return false;
		}
		
		int numGroups = parsedInput.groupCount();
		for (int i = 1; i <= numGroups; i++)
		{
			capturedGroups.add(parsedInput.group(i));
		}
		
		for (String name : groupNames)
		{
			capturedNamedGroups.put(name, nvl(parsedInput.group(name)));
		}
		return true;
	}
	
	private String nvl(String group)
	{
		return group == null ? "" : group;
	}
	
	public void setInputPattern(Pattern inputPattern)
	{
		this.inputPattern = inputPattern;
		groupNames.clear();
		populateGroupNames(inputPattern);
	}
	
	public Map<String, String> getGroupsByName()
	{
		return capturedNamedGroups;
	}
	
	public List<String> getGroupsByPosition()
	{
		return capturedGroups;
	}
}
