public class Main
{
  public static void main(String[] args)
  {
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
  }
}
