import java.io.IOException;
import java.util.Scanner;

public class Main
{
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
		//clearScreen();
		//interface loop
		Scanner readCommand = new Scanner(System.in);
		readCommand.useDelimiter(System.lineSeparator());
		boolean isRunning = true;
		String currentCommand = "";
		while (isRunning)
		{
			System.out.print("User@PCPartList: ");
			currentCommand = readCommand.next();

			//clearScreen();

			isRunning = Commands.readCommand(currentCommand);
		}
		readCommand.close();
  }
}
