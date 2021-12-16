public class Computer
{
  private ArrayList<Part> partsList;
  private float totalCost;

  public Computer()
  {
    partsList = new ArrayList<Part>();
    totalCost = 0.0f;
  }

  public Computer(ArrayList<Part> _list, float _cost)
  {
    partsList = _list;
    totalCost = _cost;
  }

  public float getCost()
  {
    return totalCost;
  }

  public ArrayList<Part> getPartsList()
  {
    return partsList;
  }

  public void setCost(float _cost)
  {
    totalCost = _cost;
  }

  public void setPartsList(ArrayList<Part> _list)
  {
    partsList = _list;
    cost = _list.getCost();
  }

  public void addPartToList(Part _part)
  {
    partsList.add(_part);
    cost += _part.getCost();
  }

  public String toString()
  {
    String final = "";

    for(int i = 0; i < partsList.size(), i++)
    {
      final += partsList[i];
      if (i + 1 != partsList.size())
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
