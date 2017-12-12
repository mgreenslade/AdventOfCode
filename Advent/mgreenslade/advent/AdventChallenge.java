package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class AdventChallenge
{
	public static final String DEFAULT_DIRECTORY = "";
	public final String fileName;
	
	public AdventChallenge(String fileName)
	{
		this.fileName = fileName;
	}
	
	public abstract String solveFirstPuzzle();
	
	public abstract String solveSecondPuzzle();
	
	public Stream<String> getInputFromFile()
	{
		try
		{
			return Files.lines(Paths.get(DEFAULT_DIRECTORY, fileName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
