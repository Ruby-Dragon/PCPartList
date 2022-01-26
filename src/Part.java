//GNU Public Licence v3, 2022, Ruby-Dragon
//
// This source is available for distribution and/or modification
// only under the terms of the PCPartList Source Code License as
// published by Ruby-Dragon. All rights reserved.

public class Part
{
	//variables
  private String name;
  private float cost;
	private float powerDraw;
	private String retailLink;
	private boolean purchased;

	//constructors
	//not really used
  public Part()
  {
    name = "";
    cost = 0.0f;
		powerDraw = 0.0f;
		retailLink = "";
		purchased = false;
  }

  public Part(String _name, float _cost, float _power)
  {
    name = _name;
    cost = _cost;
		powerDraw = _power;
		retailLink = "";
		purchased = false;
  }

	public Part(String _name, float _cost, float _power, String _link, boolean _purchased)
  {
    name = _name;
    cost = _cost;
		powerDraw = _power;
		retailLink = _link;
		purchased = _purchased;
  }

	//toString override
  public String toString()
  {
		//if it has no retail link
		if (retailLink.equals(""))
		{
			//only append PURCHASED if the item is purchased
			if (purchased)
			{
				return name + ", Price: $" + cost + ",\t Power Draw: " + powerDraw + "w,\t" + "PURCHASED";
			}
			return name + ", Price: $" + cost + ",\t Power Draw: " + powerDraw + "w";
		}
		//if it does have a retail link
		if (purchased)
		{
			return name + ", Price: $" + cost + ",\t Power Draw: " + powerDraw + "w,\t Link: " + retailLink + ",\t PURCHASED";
		}
    return name + ", Price: $" + cost + ",\t Power Draw: " + powerDraw + "w,\t Link: " + retailLink;
  }

	//getters and setters - self explanitory
  public String getName()
  {
    return name;
  }

	public void setPurchased(boolean _purchased)
	{
		purchased = _purchased;
	}

	public boolean getPurchased()
	{
		return purchased;
	}

  public float getCost()
  {
    return cost;
  }

  public void setName(String _name)
  {
    name = _name;
  }

  public void setCost(float _cost)
  {
    cost = _cost;
  }

	public void setPowerDraw(float _power)
	{
		powerDraw = _power;
	}

	public float getPowerDraw()
	{
		return powerDraw;
	}

	public String getRetailLink()
	{
		return retailLink;
	}

	public void setRetailLink(String _link)
	{
		retailLink = _link;
	}
}
