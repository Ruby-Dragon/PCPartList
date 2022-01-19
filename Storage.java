import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;

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
						write.write(encode(_save));
						//close writer
						write.close();
						return;
					}
					catch (IOException e)
					{
						//write failed
						e.printStackTrace();
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
						write.write(encode(_save));
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
		//if the file can be opened
		if (currentFile != null)
		{
			if (currentFile.canRead())
			{
				//raw input
				String raw;
				//path of file
				Path fileName = Path.of(currentFile.getName());
				try
				{
					raw = Files.readString(fileName);
				}
				catch (IOException e)
				{
					//catch IOException
					e.printStackTrace();
					return new Computer();
				}
				return decode(raw);
			}
		}
		System.out.println("File failed to open");
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
			encodedPart += currentPart.getName() + "\t" + currentPart.getCost() + "\t" + currentPart.getPowerDraw() + "\t" + currentPart.getRetailLink() + "\t" + currentPart.getPurchased() +"\n";
			//add part to final string
			finalStr += encodedPart;
			//System.out.print(encodedPart);
		}

		return finalStr;
	}

	public static Computer decode(String _pc)
	{
		long lines = _pc.lines().count(); 

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
			constructVars = lineIn.split("\t", 5);
			
			float partPrice = Float.parseFloat(constructVars[1]);
			float power = Float.parseFloat(constructVars[2]);
			boolean purchased = Boolean.parseBoolean(constructVars[4]);
			Part temp = new Part(constructVars[0], partPrice, power, constructVars[3],purchased);
			partsArray.add(temp);
		}

		return new Computer(partsArray, name);
	}
}