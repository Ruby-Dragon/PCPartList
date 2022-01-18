public class Commands
{
	public static void readCommand(String command)
	{
		int argc = 0;
		for (int i = 0; i < command.length(); i++)
		{
			if (command.charAt(i) == ' ')
			{
				argc++;
			}
		}
		String[] argv = command.split(" ", argc);

		switch (argv[0])
		{
			case "open":
				//something
				break;

			case "close":
				//exit program
				break;

			case "add":
				//add part
				break;

			case "rm":
				//remove part
				break;

			case "purchase":
				//purchase part
				break;
		}
	}
}