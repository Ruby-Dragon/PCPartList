import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Commands
{
	//computer currently open and ready for editing
	static Computer currentComputer;

	//takes the current command and figures out what it is
	public static boolean readCommand(String command)
	{
		//splits line in into an array
		String[] argv = command.split(" ");

		//switch on first statement, the command
		switch (argv[0])
		{
			case "open":
				//check for next arg for command
				if (argv.length >= 2)
				{
					//clear screen
					Main.clearScreen(); 
					//attempt to open file with arg filename
					open(argv[1]);
					//if it worked, print out the current computer
					if (currentComputer != null)
					{
						System.out.println(currentComputer);
					}
				}
				break;

			case "close":
				//exit program, return false stops loop
				return false;

			case "add":
				//add part
				//check if file is open
				if (currentComputer !=null)
				{
					//check for different args
					if (argv.length == 2)
					{
						add(argv[1], 0.0f, 0.0f);
						System.out.println(currentComputer);
					}
					else if (argv.length == 4)
					{
						if (argv[2] != "" && argv[3] != "")
						{
							add(argv[1], Float.parseFloat(argv[2]), Float.parseFloat(argv[3]));
						}
						else
						{
							add(argv[1], 0.0f, 0.0f);
						}
						System.out.println(currentComputer);
					}
					else if (argv.length >= 5)
					{
						if (argv[2] != "" && argv[3] != "")
						{
							add(argv[1], Float.parseFloat(argv[2]), Float.parseFloat(argv[3]), argv[4]);
						}
						else
						{
							add(argv[1], 0.0f, 0.0f, argv[4]);
						}
						System.out.println(currentComputer);
					}
					else
					{
						//if the args are incorrect
						System.out.println("Incorrect use of add.");
					}
				}
				else
				{
					//if no file has been opened or created
					System.out.println("No List is open.");
				}
				break;

			case "rm":
				//remove part
				if (currentComputer != null)
				{
					if (argv.length >= 2)
					{
						remove(argv[1]);
					}
					else
					{
						//if args are not correct
						System.out.println("No part specified.");
					}
				}
				else
				{
					//if no file is open or created
					System.out.println("No list is open.");
				}
				break;

			case "purchase":
				//purchase part in computer
				if (currentComputer != null)
				{
					if (argv.length >= 2)
					{
						purchase(argv[1]);
					}
					else
					{
						//if args are incorrect
						System.out.println("No part specified.");
					}
				}
				else
				{
					//if no file is open or created
					System.out.println("No list is open.");
				}
				break;

			case "edit":
				//edit part
				if (currentComputer != null)
				{
					//check args, 2 different amounts allowed
					if (argv.length == 5)
					{
						float newPower;
						float newCost;
						if (argv[3] != "" && argv[4] != "")
						{
							newPower = Float.parseFloat(argv[4]);
							newCost = Float.parseFloat(argv[3]);
							edit(argv[1], argv[2], newCost, newPower);
						}
					}
					else if (argv.length >= 6)
					{
						float newPower;
						float newCost;
						if (argv[3] != "" && argv[4] != "")
						{
							newPower = Float.parseFloat(argv[4]);
							newCost = Float.parseFloat(argv[3]);
							edit(argv[1], argv[2], newCost, newPower, argv[5]);
						}
					}
					else
					{
						//if wrong args are used
						System.out.println("Incorrect use of edit");
					}
				}
				else
				{
					//if no file is opened or created
					System.out.println("No list is open");
				}
				break;

			case "new":
				//create a new file
				if (argv.length >= 2)
				{
					newFile(argv[1]);
					Storage.writeFile(currentComputer);
					if (currentComputer != null)
					{
						System.out.println(currentComputer);
					}
				}
				break;

			case "del":
				//delete list
				if (argv.length >= 2)
				{
					deleteFile(argv[1]);
				}
				break;
		}
		return true;
	}

	private static void open(String filename)
	{
		//get path to lists folder
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		Path temp = Paths.get(path);

		temp = temp.getParent();

		path = temp.toString();
		//the reason this takes so much code is because Java will look inside of JAR

		//System.out.println(path.substring(5));

		//add .lst to end of file
		String finalFilename = path.substring(5) + "/lists/" + filename + ".lst";
		//System.out.println(finalFilename);
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Storage.openFile(finalFilename);
			currentComputer = Storage.readFile();
		}
		else
		{
			//if the file specified does not exist
			System.out.println("File: " + filename + ", does not exist!");
		}
	}

	private static void newFile(String filename)
	{
		//get path to lists folder
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		//System.out.println(path);

		Path temp = Paths.get(path);

		temp = temp.getParent();

		path = temp.toString();

		//System.out.println(path.substring(5));

		//add .lst to end of filename
		String finalFilename = path.substring(5) + "/lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			//if file already exists
			System.out.println("File: " + filename + "already exists.");
		}
		else
		{
			Main.clearScreen();
			Storage.openFile(finalFilename);
			currentComputer = new Computer();
			currentComputer.setName(filename);
		}
	}

	private static void deleteFile(String filename)
	{
		//get path to lists folder
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		//System.out.println(path);
		Path temp = Paths.get(path);

		temp = temp.getParent();

		path = temp.toString();

		//add .lst to filename
		String finalFilename = path.substring(5) + "/lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Main.clearScreen();
			if (currentComputer != null)
			{
				if (tester.getName().equals(currentComputer.getName() + ".lst"))
				{
					Main.setUserPrompt("User@PCPartList: ");
				}
			}
			tester.delete();
			Storage.closeFile();
			currentComputer = null;
			System.out.println("File deleted.");
		}
		else
		{
			//if file does not exist
			System.out.println("File: " + filename + ", does not exist.");
		}
	}

	private static void add(String partName, float cost, float powerDraw)
	{
		//make a new part from args and add to computer
		Part newPart = new Part(partName, cost, powerDraw);
		currentComputer.addPartToList(newPart);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
	}
	private static void add(String partName, float cost, float powerDraw, String retailLink)
	{
		//create a new part from args and add to computer
		Part newPart = new Part(partName, cost, powerDraw, retailLink, false);
		currentComputer.addPartToList(newPart);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
	}

	private static void remove(String partName)
	{
		//make a new array
		ArrayList<Part> newList = new ArrayList<Part>();
		//add all parts from old array except for specified part
		for (int i = 0; i < currentComputer.getPartsList().size(); i++)
		{
			if (!currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				newList.add(currentComputer.getPartsList().get(i));
			}
		}
		if (currentComputer.getPartsList().size() == newList.size())
		{
			//if nothing changed between arrays
			System.out.println("The part with name: " + partName + ", was not found.");
		}
		else
		{
			//set computer array to the new one created here
			currentComputer.setPartsList(newList);
			Storage.writeFile(currentComputer);
			Main.clearScreen();
			System.out.println(currentComputer);
		}
	}

	public static void purchase(String partName)
	{
		//search through array for part specified
		for (int i =0; i < currentComputer.getPartsList().size(); i++)
		{
			if (currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				//purchase part if it's name matches
				currentComputer.getPartsList().get(i).setPurchased(true);
			}
		}
		Storage.writeFile(currentComputer);
		Main.clearScreen();
		System.out.println(currentComputer);
	}

	public static void edit(String partName, String newName, float newPrice, float newPower)
	{
		//make a new part and array
		Part newPart = new Part(newName, newPrice, newPower);
		ArrayList<Part> newArray = currentComputer.getPartsList();
		//add all unspecified parts to new array
		for (int i=0; i < currentComputer.getPartsList().size(); i++)
		{
			if (currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				//replace part in location with new part
				newArray.set(i, newPart);
			}
		}
		currentComputer.setPartsList(newArray);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
		System.out.println(currentComputer);
	}

	public static void edit(String partName, String newName, float newPrice, float newPower, String retailLink)
	{
		//overload is same, except part has a retail link
		Part newPart = new Part(newName, newPrice, newPower, retailLink, false);
		ArrayList<Part> newArray = currentComputer.getPartsList();
		for (int i=0; i < currentComputer.getPartsList().size(); i++)
		{
			if (currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				newArray.set(i, newPart);
			}
		}
		currentComputer.setPartsList(newArray);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
		System.out.println(currentComputer);
	}

}