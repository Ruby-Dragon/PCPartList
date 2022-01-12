public class Part
{
	//variables
  private String name;
  private float cost;
	private float powerDraw;

	//constructors
  public Part()
  {
    name = "";
    cost = 0.0f;
		powerDraw = 0.0f;
  }

  public Part(String _name, float _cost, float _power)
  {
    name = _name;
    cost = _cost;
		powerDraw = _power;
  }

	//toString override
  public String toString()
  {
    return name + ", Price: $" + cost + ", Power Draw: " + powerDraw + "w";
  }

	//getters and setters
  public String getName()
  {
    return name;
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
}
