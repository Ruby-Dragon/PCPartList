import java.util.ArrayList;

public class Main
{
  public static void main(String[] args)
  {
    Computer comp = new Computer();
    Part cpu = new Part("Xeon e5 2690 v3", 54.24f, 180.0f);		

    comp.addPartToList(cpu);
		comp.setName("comp");

    System.out.println(comp);
  }
}
