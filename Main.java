import java.io.IOException;

public class Main
{
	public static void clearScreen(){
    //Clears Screen in java
    try 
		{
				//windows clear screen
        if (System.getProperty("os.name").contains("Windows"))
				{
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				}
        else
				{
					//Linux\Unix clear screen (probably the best idea)
					Runtime.getRuntime().exec("clear");
				} 
    } 
		catch (IOException | InterruptedException ex) 
		{
			ex.printStackTrace();
		}
	}

  public static void main(String[] args)
  {
		//testing
    Computer comp = new Computer();
    Part cpu = new Part("Xeon e5 2690 v3", 54.24f, 180.0f);		
		Part gpu = new Part("GTX 680", 200.0f, 195.0f, "https://www.ebay.com/itm/185251093760");
		Part psu = new Part("Corsair CX750", 80.0f, 0.0f);

    comp.addPartToList(cpu);
		comp.addPartToList(gpu);
		comp.addPartToList(psu);
		comp.setName("comp");

		Storage.openFile("mySickBuild.lst");
		Storage.writeFile(comp);

		System.out.println(Storage.readFile());

		clearScreen();
		//interface loop
  }
}
