public class Main
{
  public static void main(String[] args)
  {
    Computer comp = new Computer();
    Part cpu = new Part("Xeon e5 2690 v3", 54.24f, 180.0f);		
		Part gpu = new Part("GTX 680", 200.0f, 195.0f, "https://www.ebay.com/itm/185251093760");

    comp.addPartToList(cpu);
		comp.addPartToList(gpu);
		comp.setName("comp");

		Storage.openFile("urmom.lst");
		Storage.writeFile(comp);

    

		System.out.println(Storage.readFile());
  }
}
