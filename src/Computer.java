//GNU Public Licence v3, 2022, Ruby-Dragon
//
// This source is available for distribution and/or modification
// only under the terms of the PCPartList Source Code License as
// published by Ruby-Dragon. All rights reserved.

import java.util.ArrayList;

public class Computer
{
	//variables
  private ArrayList<Part> partsList;
  private float totalCost;
	private float totalPowerDraw;
	private String name;


	//constructors
	//not used often if at all
  public Computer()
  {
    partsList = new ArrayList<Part>();
    totalCost = 0.0f;
		name = "";
		totalPowerDraw = 0.0f;
  }

	//used a lot
  public Computer(ArrayList<Part> _list, String _name)
  {
    partsList = _list;
    recalculateCost();
		name = _name;
		recalculatePowerDraw();
  }

	//getters and setters
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

	//recalculates the powerdraw based on parts in the array
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

	//recalculates the cost based on parts in the array
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

	//returns a string in a nice format to print on screen
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
