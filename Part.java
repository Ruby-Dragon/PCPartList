public class Part
{
  private String name;
  private float cost;

  public Part()
  {
    name = "";
    cost = 0.0f;
  }

  public Part(String _name, float _cost)
  {
    name = _name;
    cost = _cost;
  }

  public String toString()
  {
    return name + ", Price: " + cost;
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
