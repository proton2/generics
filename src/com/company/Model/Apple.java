package com.company.Model;

/**
 * Created by b.yacenko on 27.09.2017.
 */
public class Apple extends Fruit implements Comparable<Apple>{
  private String sort;

  public String getSort() {
    return sort;
  }

  public void setSort(String aSort) {
    sort = aSort;
  }

  public Apple() {

  }

  public Apple(String aName) {
    super(aName);
  }

  public Apple(String aName, String aSort) {
    super(aName);
    this.sort = aSort;
  }

  @Override
  public int compareTo(Apple o) {
    return this.getName().length() - o.getName().length();
  }
}
