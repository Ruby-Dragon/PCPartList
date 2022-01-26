import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Commands
{
	//computer currently open and ready for editing
	static Computer currentComputer;

	public static boolean readCommand(String command)
	{
		String[] argv = command.split(" ");

		/*
		for (String arg : argv)
		{
			System.out.println(arg + ";");
		}
		*/

		switch (argv[0])
		{
			case "open":
				if (argv.length >= 2)
				{
					Main.clearScreen();
					open(argv[1]);
					if (currentComputer != null)
					{
						System.out.println(currentComputer);
					}
				}
				break;

			case "close":
				//exit program
				return false;

			case "add":
				//add part
				if (currentComputer !=null)
				{
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
						System.out.println("Incorrect use of add.");
					}
				}
				else
				{
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
						System.out.println("No part specified.");
					}
				}
				else
				{
					System.out.println("No list is open.");
				}
				break;

			case "purchase":
				if (currentComputer != null)
				{
					if (argv.length >= 2)
					{
						purchase(argv[1]);
					}
					else
					{
						System.out.println("No part specified.");
					}
				}
				else
				{
					System.out.println("No list is open.");
				}
				break;

			case "edit":
				//edit part
				if (currentComputer != null)
				{
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
						System.out.println("Incorrect use of edit");
					}
				}
				else
				{
					System.out.println("No list is open");
				}
				break;

			case "new":
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
					System.out.println("File deleted.");
				}
				break;
		}
		return true;
	}

	private static void open(String filename)
	{
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		Path temp = Paths.get(path);

		temp = temp.getParent();

		path = temp.toString();

		System.out.println(path.substring(5));

		String finalFilename = path.substring(5) + "/lists/" + filename + ".lst";
		System.out.println(finalFilename);
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Storage.openFile(finalFilename);
			currentComputer = Storage.readFile();
		}
		else
		{
			System.out.println("File: " + filename + ", does not exist!");
		}
	}

	private static void newFile(String filename)
	{
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		//System.out.println(path);

		String finalFilename = path + "lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
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
		Class<?> c = Main.class;
		String path = c.getResource(c.getSimpleName() + ".class").getPath().replace(c.getSimpleName() + ".class", "");

		//System.out.println(path);

		String finalFilename = path + "lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Main.clearScreen();
			tester.delete();
			Storage.closeFile();
			currentComputer = new Computer();
		}
		else
		{
			System.out.println("File: " + filename + ", does not exist.");
		}
	}

	private static void add(String partName, float cost, float powerDraw)
	{
		Part newPart = new Part(partName, cost, powerDraw);
		currentComputer.addPartToList(newPart);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
	}
	private static void add(String partName, float cost, float powerDraw, String retailLink)
	{
		Part newPart = new Part(partName, cost, powerDraw, retailLink, false);
		currentComputer.addPartToList(newPart);
		Storage.writeFile(currentComputer);
		Main.clearScreen();
	}

	private static void remove(String partName)
	{
		ArrayList<Part> newList = new ArrayList<Part>();
		for (int i = 0; i < currentComputer.getPartsList().size(); i++)
		{
			if (!currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				newList.add(currentComputer.getPartsList().get(i));
			}
		}
		if (currentComputer.getPartsList().size() == newList.size())
		{
			System.out.println("The part with name: " + partName + ", was not found.");
		}
		else
		{
			currentComputer.setPartsList(newList);
			Storage.writeFile(currentComputer);
			Main.clearScreen();
			System.out.println(currentComputer);
		}
	}

	public static void purchase(String partName)
	{
		for (int i =0; i < currentComputer.getPartsList().size(); i++)
		{
			if (currentComputer.getPartsList().get(i).getName().equals(partName))
			{
				currentComputer.getPartsList().get(i).setPurchased(true);
			}
		}
		Storage.writeFile(currentComputer);
		Main.clearScreen();
		System.out.println(currentComputer);
	}

	public static void edit(String partName, String newName, float newPrice, float newPower)
	{
		Part newPart = new Part(newName, newPrice, newPower);
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

	public static void edit(String partName, String newName, float newPrice, float newPower, String retailLink)
	{
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