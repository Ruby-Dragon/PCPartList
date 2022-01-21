import java.util.ArrayList;

public class Computer
{
  private ArrayList<Part> partsList;
  private float totalCost;
	private float totalPowerDraw;
	private String name;

  public Computer()
  {
    partsList = new ArrayList<Part>();
    totalCost = 0.0f;
		name = "";
		totalPowerDraw = 0.0f;
  }

  public Computer(ArrayList<Part> _list, String _name)
  {
    partsList = _list;
    recalculateCost();
		name = _name;
		recalculatePowerDraw();
  }

	public String getName()
	{
		return name;
	}

	public void setName(String _name)
	{
		name = _name;
	}

	public float getTotalPowerDraw()
	{
		return totalPowerDraw;
	}

	public void recalculatePowerDraw()
	{
		float sum = 0;
		for (Part currentPart : partsList)
		{
			sum += currentPart.getPowerDraw();
		}
		totalPowerDraw = sum;
	}

  public float getCost()
  {
    return totalCost;
  }

 	public void recalculateCost()
  {
    float sum = 0;
		for (Part currentPart : partsList)
		{
			sum += currentPart.getCost();
		}
		totalCost = sum;
  }

  public ArrayList<Part> getPartsList()
  {
    return partsList;
  }

  public void setPartsList(ArrayList<Part> _list)
  {
    partsList = _list;
    recalculateCost();
		recalculatePowerDraw();
  }

  public void addPartToList(Part _part)
  {
    partsList.add(_part);
    recalculateCost();
		recalculatePowerDraw();
  }

  public String toString()
  {
    String finalStr = "";

		finalStr += name + ", $" + totalCost + ", wattage: " + totalPowerDraw + "w. \nParts: \n"; 

    for(int i=0; i < partsList.size(); i++)
    {
      finalStr += "	- " + partsList.get(i);
      if (i + 1 != partsList.size())
      {
        finalStr += "\n";
      }
      else
      {
        finalStr += ".";
      }
    }

    return finalStr;
  }
}
