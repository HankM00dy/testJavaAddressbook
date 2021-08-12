package ru.stqa.pft.addressbook;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {

//        // Определение массива может быть таким

//        String[] langs = new String[4];
//        langs[0] = "Java";
//        langs[1] = "C#";
//        langs[2] = "Python";
//        langs[3] = "PHP";
//        for (int i = 0; i < langs.length; i++) {
//            System.out.println("Я хочу выучить " + langs[i]);
//        }


//        // Более короткое определение массива
//        String[] langs = {"Java", "C#", "Python", "PHP"};
//        for (int i = 0; i < langs.length; i++) {
//            System.out.println("Я хочу выучить " + langs[i]);
//        }

//        // С массивами можно работать в коллекции, в таком случае переменная-счетчик "i" не создается, обращение идет напрямую к элементу массива
//        String[] langs = {"Java", "C#", "Python", "PHP"};
//
//        for (String lang : langs) {
//            System.out.println("Я хочу выучить " + lang);
//        }


//        // Определение упорядоченного списка
//        List<String> langs = new ArrayList();
//        langs.add("Java");
//        langs.add("C#");
//        langs.add("Python");
//        langs.add("PHP");
//
//        for (String lang : langs) {
//            System.out.println("Я хочу выучить " + lang);
//        }

        // Еще один способ обозначить упорядоченный список
        List<String> langs = Arrays.asList("Java", "C#", "Python", "PHP");

//        // Перебирать можно через переменную-счетчик "i"
//        for (int i = 0; i < langs.size(); i++) {
//            System.out.println("Я хочу выучить " + langs.get(i));

        for (String lang : langs) {
            System.out.println("Я хочу выучить " + lang);
        }
    }
}
