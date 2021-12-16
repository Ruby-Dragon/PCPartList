public class Computer
{
  private Part[] partsList;
  private float totalCost;

  public Computer()
  {
    partsList = {};
    totalCost = 0.0f;
  }

  public Computer(Part[] _list, float _cost)
  {
    partsList = _list;
    totalCost = _cost;
  }

  public float getCost()
  {
    return totalCost;
  }

  public Part[] getPartsList()
  {
    return partsList;
  }

  public void setCost(float _cost)
  {
    totalCost = _cost;
  }

  public void setPartsList(Part[] _list)
  {
    partsList = _list;
  }

  public String toString()
  {
    String final = "";

    for(int i = 0; i < partsList.length, i++)
    {
      final += partsList[i];
      if (i + 1 != partsList.length)
      {
        final += ", ";
      }
      else
      {
        final += ".";
      }
    }

    return final;
  }
}
