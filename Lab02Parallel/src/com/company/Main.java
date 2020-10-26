package com.company;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Main {
    public static Pizza findCheapestSpicy(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .anyMatch(i->i.isSpicy()))
                .min((p1,p2)->p1.getIngredients().stream().mapToInt(i->i.getPrice()).sum()-
                        p2.getIngredients().stream().mapToInt(i->i.getPrice()).sum())
                .orElse(null);
    }
    public static Pizza findMostExpensiveVegetarian(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .noneMatch(i->i.isMeat()))
                .max((p1,p2)->p1.getIngredients().stream().mapToInt(i->i.getPrice()).sum()-
                        p2.getIngredients().stream().mapToInt(i->i.getPrice()).sum())
                .orElse(null);
    }
    public static List<Pizza> iLikeMeat(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .anyMatch(i->i.isMeat()))
                .sorted(Comparator.comparing((Pizza p)->p.getIngredients().stream().filter(i->i.isMeat()).count()).reversed())
                .collect(toList());
    }
    public static Map<Integer,List<Pizza>> groupByPrice(List<Pizza> pizzaList){
        return pizzaList.stream()
                .sorted(Comparator.comparing((Pizza p)->p.getName()))
                .collect(groupingBy(p->p.getIngredients().stream().mapToInt(i->i.getPrice()).sum()));
    }
    //format pizzaName : ing1,ing2 - price newline pizzaName...
    public static String formatedMenu(List<Pizza> pizzaList){
        return pizzaList.stream()
                .map(p->String.format("%s: %s - %d",
                        p.toString(),
                        p.getIngredients().stream().map(i->i.getName()).collect(joining(", ")),
                        p.getIngredients().stream().mapToInt(i->i.getPrice()).sum()))
                .collect(joining("\n"));
    }

    public static Pizza findMostIngredientsVege(List<Pizza> pizzas){
        return pizzas.stream()
                .filter(p->p.getIngredients()
                        .stream()
                        .noneMatch(i->i.isMeat()))
                .max(Comparator.comparing(p->p.getIngredients().stream().count()))
                .orElse(null);
    }

    public static List<Pizza> findThreeCheapestMeaty(List<Pizza> pizzas){
        return pizzas.stream().limit(4)
                .filter(p->p.getIngredients().stream().anyMatch(i->i.isMeat()))
                .sorted((p1,p2)->p1.getIngredients().stream().mapToInt(i->i.getPrice()).sum()-
                        p2.getIngredients().stream().mapToInt(i->i.getPrice()).sum())
                .collect(toList());
    }

    public static String groupSpicyByPrice(List<Pizza> pizzaList){
        return pizzaList.stream()
                .filter(p->p.getIngredients().stream().anyMatch(i->i.isSpicy()))
                .sorted(Comparator.comparing((Pizza p)->p.getIngredients().stream()
                        .mapToInt(i -> i.getPrice())
                        .sum()).reversed())
                .map(p->String.format("%s: %d",
                        p.toString(),
                        p.getIngredients().stream().mapToInt(i->i.getPrice()).sum()))
                .collect(joining("\n"));
    }

    public static void main(String[] args) {
        List<Pizza>pizzaList=new ArrayList<>(EnumSet.allOf(Pizza.class));
        System.out.println("Select option:\n 1)find cheapest spicy\n 2)find most ingr vege\n 3)find top 3 cheap meat pizzas\n 4)group spicy pizzas by price\n 5)full menu\n 6)exit");
        Scanner input=new Scanner(System.in);
        int menuOpt=input.nextInt();
        switch(menuOpt){
            case 1:
//                System.out.println(findCheapestSpicy(pizzaList));
                break;
            case 2:
                System.out.println(findMostIngredientsVege(pizzaList));
//                System.out.println(findMostExpensiveVegetarian(pizzaList));
                break;
            case 3:
//                System.out.println(iLikeMeat(pizzaList));
                System.out.println(findThreeCheapestMeaty(pizzaList));
                break;
            case 4:
//                System.out.println(groupByPrice(pizzaList));
                System.out.println(groupSpicyByPrice(pizzaList));
                break;
            case 5:
                System.out.println(formatedMenu(pizzaList));
                break;
            case 6:
                System.exit(0);
                break;
        }
    }

}
