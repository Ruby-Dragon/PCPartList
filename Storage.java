import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Storage
{
	private static File currentFile;

	public static void openFile(String filename)
	{
		currentFile = new File(filename);
	}

	public static void writeFile(Computer _save)
	{
		if (currentFile != null)
		{
				if (currentFile.exists())
				{
					try
					{
						FileWriter write = new FileWriter(currentFile);
						write.write("ur mom");
						write.close();
						return;
					}
					catch (IOException e)
					{
						System.out.println("Write failed with IOException");
						return;
					}
				}
				else
				{
					try
					{
						currentFile.createNewFile();
						FileWriter write = new FileWriter(currentFile);
						write.write("ur mom");
						write.close();
						return;
					}
					catch (IOException e)
					{
						System.out.println("Write failed with IOException");
						return;
					}
			}
		}
		System.out.println("File not opened");
	}

	public static Computer readFile()
	{
		if (currentFile != null)
		{
			if (currentFile.canRead())
			{
				return new Computer();
			}
		}
		System.out.println("File not opened");
		return new Computer();
	}
}

