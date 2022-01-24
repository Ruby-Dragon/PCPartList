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
				break;
		}
		return true;
	}

	private static void open(String filename)
	{
		String finalFilename = "builds/lists/" + filename + ".lst";
		File tester = new File(finalFilename);
		if (tester.exists())
		{
			Storage.openFile(finalFilename);
			currentComputer = Storage.readFile();
		}
		else
		{
			System.out.println("File: " + finalFilename + ", does not exist");
		}
	}

	private static void newFile(String filename)
	{
		String finalFilename = "builds/lists/" + filename + ".lst";
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

}