package com.company;

import com.company.Model.Apple;
import com.company.Model.Fruit;
import com.company.Model.Golden;

/**
 * Created by proton2 on 28.09.2017.
 */
public class GenericTypesExample {
  public static void main(String[] args) {
    example1();
  }

  private void example1(){
    // можно передать только тот тип аргумента, который наследуется от Fruit и имплементит Comparable
    compareExample(new Fruit(), new Apple()); // error
    compareExample(new Golden("name1"), new Apple("nameee2"));
  }

  // ограничение типа аргумента - можно передать аргумент унаследованный от Fruit который имплементит Comparable
  private static <T extends Fruit & Comparable<T>> void compareExample(T arg1, T arg2){
    int res = arg1.compareTo(arg2);
    System.out.println(res < 1 ? arg1.getName() : arg2.getName());
  }
}
