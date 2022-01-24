import java.io.File;

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
				break;

			case "rm":
				//remove part
				break;

			case "purchase":
				//purchase part
				break;

			case "edit":
				//edit part
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

		//System.out.println(path);

		String finalFilename = path + "lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Storage.openFile(finalFilename);
			currentComputer = Storage.readFile();
		}
		else
		{
			System.out.println("File: " + filename + ", does not exist");
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
	}
	private static void add(String partName, float cost, float powerDraw, String retailLink)
	{
		Part newPart = new Part(partName, cost, powerDraw, retailLink, false);
		currentComputer.addPartToList(newPart);
	}

}