package com.company;

import com.company.Model.Apple;
import com.company.Model.Fruit;
import com.company.Model.Golden;

import java.util.ArrayList;
import java.util.List;

// Учебный пример, создан в целях наглядной демонстрации особенностей Generic'ов

public class GenericWildcardExample {
    public static void main(String[] args) {
        example1();
    }

    // Можно типизировать лист родительским типом и класть в него потомков от типа, которым типизирован лист.
    // Но достать из листа можно только тот тип, которым он был типизирован.
    private static void example1(){
        Fruit fruit = new Fruit("kiwi");
        Apple apple = new Apple("Red", "Golden");

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(fruit);
        fruits.add(apple);

        // можно достать только Fruit, не Apple
        Apple apple1 = fruits.get(0);
        Fruit apple2 = fruits.get(0);
        System.out.println(apple1.toString());
    }

    private static void example2(){
        List<Apple> apples = new ArrayList<>();

        Apple apple = new Apple("Red", "Golden");
        apples.add(apple);

        //дженерифицированные типы не ковариантны. Полиморфизм у дженериков не работает.
        List<Fruit> fruits = apples; // error

        // расширяем тип у List<Apples> apples, приводим его к листу типа <неизвестный тип - потомок Fruit
        List<? extends Fruit> fruitsExtends = apples;
        Apple apple2 = new Apple("yellow", "Ranetka");

        // ? extends Fruit - неизвестный тип. Метод get имеет аргумент типа <T>. Он не пропустит аргумент не известного типа ? extends Fruit,
        // пропустит только четкий тип <T>
        fruitsExtends.add(apple2); // error
        fruitsExtends.add(new Golden()); // error
        // если сделать НЕ БЕЗОПАСНОЕ явное приведение - тогда работает как обычный тип <T>
        ((List<Apple>)fruitsExtends).add(apple2);

        // расширяем тип у List<Fruit> fruits, приводим его к листу типа <неизвестный тип - родитель Apple
        List<? super Apple> fruitsSuper = fruits;
        // ? super Apple - какой-то не известный тип, родитель Apple. Значит все что выше Apple передать в аргумент метода нельзя
        // но в метод можно безопасно передать любого потомка Apple
        fruitsSuper.add(apple2);

        // можно достать только Fruit, не Apple.
        Fruit apple4 = fruitsExtends.get(0);
        //У <? extends Fruit> четко известен верхний тип только Object
        Fruit apple5 = fruitsSuper.get(0); // error
        Object apple3 = fruitsSuper.get(0);

        // можно передать любой лист - наследник Fruit
        setList(apples);
    }

    private static void setList(List<? extends Fruit> var){
        Apple apple = new Apple("Red", "Golden");
        // при типизации объекта ? extends T если в методе тип аргумента <T>, то передать в метод нельзя ничего
        var.add(apple); // error

        // а вернуть из метода можно тип аргумента который указан после слова extends, не ниже
        Apple ap = var.get(0); // error
        Fruit ss = var.get(0);
        // то же правило для возвращения значения из итератора
        for(Fruit f : var){
            System.out.println(f.getName());
        }

        // если в методе тип аргумента Object, то в метод при типизации объекта ? extends T можно передать любой объект
        boolean res = var.equals(new ArrayList<>());
        System.out.println(res);
    }
}