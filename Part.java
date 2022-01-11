public class Part
{
  private String name;
  private float cost;
	private float powerDraw;

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

  public String toString()
  {
    return name + ", Price: $" + cost + ", Power Draw: " + powerDraw + "w";
  }

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
}
