//GNU Public Licence v3, 2022, Ruby-Dragon
//
// This source is available for distribution and/or modification
// only under the terms of the PCPartList Source Code License as
// published by Ruby-Dragon. All rights reserved.

import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;

public class Storage
{
	//the current file that is open
	private static File currentFile;

	//open a file and store it's location
	public static void openFile(String filename)
	{
		currentFile = new File(filename);
		Main.setUserPrompt("User@PCPartList/" + currentFile.getName() + ": ");
	}

	//close the current file - set it to null
	public static void closeFile()
	{
		currentFile = null;
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
						e.printStackTrace();
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
				Path fileName = Path.of(currentFile.getAbsolutePath());
				//System.out.println(fileName);
				try
				{
					//read
					raw = Files.readString(fileName);
				}
				catch (IOException e)
				{
					//catch IOException
					e.printStackTrace();
					return new Computer();
				}
				//decode raw input
				return decode(raw);
			}
		}
		//if the file did not open
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

		//price and power consumption cannot be removed from encoder or decoder
		//without rewriting the way they work - takes a lot of time

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
		//not actually needed anymore
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
		//not actually needed anymore
		powerDraw = Float.parseFloat(powerString);

		//Decode Parts
		ArrayList<Part> partsArray = new ArrayList<Part>();
		for (int i = 0; i < lines -3; i++)
		{
			//an array of variables to be put into Part constructor
			String[] constructVars;
			//current line input
			String lineIn = "";
			//iterate through line
			for (int c = lastIndex + 1; c < _pc.length(); c++)
			{
				//break at end of line
				if (_pc.charAt(c) == '\n')
				{
					lastIndex = c;
					break;
				}
				else
				{
					//add to line in
					lineIn += _pc.charAt(c);
				}
			}
			
			//if there is a line with data on it
			if (lineIn != "")
			{
				//split the line in into an array
				constructVars = lineIn.split("\t", 5);
				//parse price
				float partPrice = Float.parseFloat(constructVars[1]);
				//parse power consumption
				float power = Float.parseFloat(constructVars[2]);
				//parse purchased
				boolean purchased = Boolean.parseBoolean(constructVars[4]);
				//create new Part from data from line in
				Part temp = new Part(constructVars[0], partPrice, power, constructVars[3],purchased);
				//ad part to array of Parts, and then keep going to the next line
				partsArray.add(temp);
			}
		}
		//return a Computer with the name and array of parts
		return new Computer(partsArray, name);
	}
}