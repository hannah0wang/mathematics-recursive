import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * The driver class to have sanity check on MathematicsRec class
 * @author Martin Hock <mhock@tacomacc.edu>
 */
public class MathematicsTest
{
	public static void rowColumn(String source, int offset)
	{
		int row = 1;
		int col = 1;
		for (int i = 0; i < offset; i++)
		{
			if (source.charAt(i) == '\n')
			{
				row++;
				col = 1;
			}
			else if (source.charAt(i) == '\t')
				col += (4 - (col - 1) % 4);
			else
				col++;
		}
		System.out.println("Above problem is on line " + row + " column " + col + " (Assuming tab width of 4)");
	}

	public static void main(String[] args) throws NoSuchAlgorithmException
	{
		String source = null;
		
		try
		{
			source = new String(Files.readAllBytes(Paths.get("src" + File.separator + "MathematicsRec.java")));
		}
		catch (Exception e)
		{
			System.out.println("Couldn't find MathematicsRec.java! Run this from the same Eclipse project as MathematicsRec.");
			return;
		}
		if (source.matches("(?s).*\\sfor[\\s\\(].*"))
		{
			System.out.println("Detected 'for' statement in your program! Please remove anything that resembles a 'for'.");
			System.exit(-1);
		}
		if (source.matches("(?s).*\\swhile[\\s\\(].*"))
		{
			System.out.println("Detected 'while' statement in your program! Please remove anything that resembles a 'while'.");
			System.exit(-1);
		}
		if (source.matches("(?s).*\\simport\\s.*") ||
			source.indexOf("import") == 0)
		{
			System.out.println("Detected 'import' statement in your program! Please remove any word 'import'.");
			System.exit(-1);
		}
		if (source.matches("(?s).*\\spublic\\s+static\\s+\\w+[^(]*=.*") ||
			source.matches("(?s).*\\spublic\\s+static\\s+\\w+[^(]*;.*"))
		{
			System.out.println("Detected static variable in your program! Please remove static variables.");
			System.exit(-1);
		}
		for (int dotIndex = source.indexOf('.'); dotIndex != -1; dotIndex = source.indexOf('.', dotIndex + 1))
		{
			if ("System.out.println".equals(source.substring(dotIndex - 6, dotIndex + 12))
					|| "System.out.println".equals(source.substring(dotIndex - 10, dotIndex + 8))
					|| "length".equals(source.substring(dotIndex + 1, dotIndex + 7))
					|| Character.isDigit(source.charAt(dotIndex + 1)))
			{
				continue;
			}
			System.out.println("Bad dot! Context: "
					+ source.substring(Math.max(0, dotIndex - 20), Math.min(dotIndex + 20, source.length())));
			rowColumn(source, dotIndex);
			System.exit(-1);
		}

		System.out.println("Passed syntax checks!");
	}
}