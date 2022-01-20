import java.io.IOException;
import java.util.Scanner;

public class Main
{

	private static String userPrompt;

	public static void setUserPrompt(String _prompt)
	{
		userPrompt = _prompt;
	}

	public static void clearScreen(){
    //Clears Screen in java
    try 
		{
				//windows clear screen
        if (System.getProperty("os.name").contains("Windows"))
				{
					//hope this works
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				}
        else
				{
					//Linux\Unix clear screen (probably the best idea)
					System.out.print("\033[H\033[2J");  
   				System.out.flush();  
				} 
    } 
		catch (IOException | InterruptedException ex) 
		{
			ex.printStackTrace();
		}
	}

  public static void main(String[] args)
  {
		userPrompt = "User@PCPartList: ";
		//clearScreen();
		//interface loop
		Scanner readCommand = new Scanner(System.in);
		readCommand.useDelimiter(System.lineSeparator());
		boolean isRunning = true;
		String currentCommand = "";
		while (isRunning)
		{
			System.out.print(userPrompt);
			currentCommand = readCommand.next();

			//clearScreen();

			isRunning = Commands.readCommand(currentCommand);
		}
		readCommand.close();
  }
}
