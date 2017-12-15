package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class AdventChallenge
{
	public static final String ROOT = "mgreenslade";
	public static final String RESOURCES = "resources";
	public final String fileName;
	public final String name;
	
	public AdventChallenge(String name, String fileName)
	{
		this.name = name;
		this.fileName = fileName;
	}
	
	public abstract String solveFirstPuzzle();
	
	public abstract String solveSecondPuzzle();
	
	public Stream<String> getInputFromFile()
	{
		try
		{
			return Files.lines(Paths.get(ROOT, RESOURCES, fileName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSingleLineInput()
	{
		return getInputFromFile().findFirst().get();
	}
}
