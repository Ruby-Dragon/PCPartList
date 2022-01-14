import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage
{
	private static File currentFile;

	public static void openFile(String filename)
	{
		currentFile = new File(filename);
	}

	public static void writeFile(Computer _save)
	{
		//if a computer file is open
		if (currentFile != null)
		{
				//if the file exists, use that, if it doesn't, make one
				if (currentFile.exists())
				{
					//check for errors
					try
					{
						//create a writer
						FileWriter write = new FileWriter(currentFile);
						//write information into file
						write.write("ur mom");
						//close writer
						write.close();
						return;
					}
					catch (IOException e)
					{
						//write failed
						System.out.println("Write failed with IOException");
						return;
					}
				}
				else
				{
					try
					{
						//create a new file with the opened file name
						currentFile.createNewFile();
						//create writer
						FileWriter write = new FileWriter(currentFile);
						//write information into file
						write.write("ur mom");
						//close writer
						write.close();
						return;
					}
					catch (IOException e)
					{
						//write error
						System.out.println("Write failed with IOException");
						return;
					}
			}
		}
		//if the file was never opened
		System.out.println("File not opened");
	}

	public static Computer readFile()
	{
		//needs encode and decode to be done first
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

	public static String encode(Computer _pc)
	{
		String finalStr = "";
		//add 3 line header of Computer information
		finalStr += _pc.getName() + "\n" + _pc.getCost() + "\n" + _pc.getTotalPowerDraw() + "\n";
		//System.out.print(finalStr);

		//part in encoded form
		String encodedPart = "";
		//encode parts list
		for (Part currentPart : _pc.getPartsList())
		{
			encodedPart = "";
			//add part vars separated by a /t on one line
			encodedPart += currentPart.getName() + "\t" + currentPart.getCost() + "\t" + currentPart.getPowerDraw() + "\t\n";
			//add part to final string
			finalStr += encodedPart;
			//System.out.print(encodedPart);
		}

		return finalStr;
	}

	public static Computer decode(String _pc)
	{
		long lines = _pc.lines().count();
		System.out.println(lines); 

		//last index iterated through by decoder
		int lastIndex = 0;

		//Decode name
		String name = "";
		//iterate until \n
		for (int i = 0; i < _pc.length(); i++)
		{
			//break at \n
			if (_pc.charAt(i) == '\n')
			{
				lastIndex = i;
				break;
			}
			else
			{
				//add character to name string
				name += _pc.charAt(i);
			}
		}
		System.out.println(name);

		//Decode price
		String priceString = "";
		float price = 0.0f;
		//iterate through string
		for (int i = lastIndex + 1; i < _pc.length(); i++)
		{
			//break at \n
			if (_pc.charAt(i) == '\n')
			{
				lastIndex = i;
				break;
			}
			else
			{
				//add character to name string
				priceString += _pc.charAt(i);
			}
		}
		price = Float.parseFloat(priceString);
		System.out.println(price);

		//Decode powerDraw
		String powerString = "";
		float powerDraw = 0.0f;
		//iterate through string
		for (int i = lastIndex + 1; i < _pc.length(); i++)
		{
			//break at \n
			if (_pc.charAt(i) == '\n')
			{
				lastIndex = i;
				break;
			}
			else
			{
				//add character to name string
				powerString += _pc.charAt(i);
			}
		}
		powerDraw = Float.parseFloat(powerString);
		System.out.println(powerDraw);

		//Decode Parts
		ArrayList<Part> partsArray = new ArrayList<Part>();
		for (int i = 0; i < lines -3; i++)
		{
			String[] constructVars;
			String lineIn = "";
			for (int c = lastIndex + 1; c < _pc.length(); c++)
			{
				if (_pc.charAt(c) == '\n')
				{
					lastIndex = c;
					break;
				}
				else
				{
					lineIn += _pc.charAt(c);
				}
			}
			constructVars = lineIn.split("\t", 4);
			for (int t =0; t < 3; t++)
			{
				System.out.print(constructVars[t] + " ");
			}
		}

		return new Computer();
	}
}