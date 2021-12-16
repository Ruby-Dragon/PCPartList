import java.util.ArrayList;

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
    float tempCost = 0.0f;
    for (int i=0; i < _list.size(); i++)
    {
      tempCost += _list.get(i).getCost();
    }
  }

  public void addPartToList(Part _part)
  {
    partsList.add(_part);
    totalCost += _part.getCost();
  }

  public String toString()
  {
    String finalStr = "";

    for(int i=0; i < partsList.size(); i++)
    {
      finalStr += partsList.get(i);
      if (i + 1 != partsList.size())
      {
        finalStr += ", ";
      }
      else
      {
        finalStr += ".";
      }
    }

    return finalStr;
  }
}
