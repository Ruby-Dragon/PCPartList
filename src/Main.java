//GNU Public Licence v3, 2022, Ruby-Dragon
//
// This source is available for distribution and/or modification
// only under the terms of the PCPartList Source Code License as
// published by Ruby-Dragon. All rights reserved.

import java.io.IOException;
import java.util.Scanner;

public class Main
{
	//userprompt
	private static String userPrompt;

	//change userprompt
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
					//should work - untested though
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				}
        else
				{
					//Linux\Unix clear screen (probably the best implementation)
					System.out.print("\033[H\033[2J");  
   				System.out.flush();  
				} 
    } 
		catch (IOException | InterruptedException ex) //should not happen at all
		{
			ex.printStackTrace();
		}
	}

  public static void main(String[] args)
  {
		//default prompt
		userPrompt = "User@PCPartList: ";
		
		//scanner for input
		Scanner readCommand = new Scanner(System.in);
		//use line separator as a delimiter
		readCommand.useDelimiter(System.lineSeparator());
		boolean isRunning = true;
		//current command
		String currentCommand = "";
		//interface loop
		while (isRunning)
		{
			//print prompt
			System.out.print(userPrompt);
			//read command
			currentCommand = readCommand.next();

			//interpret command
			isRunning = Commands.readCommand(currentCommand);
		}
		//close scanner at close
		readCommand.close();
  }
}
