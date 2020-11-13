package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class Main {

    public static List<Supply> sortedSupplies(List<Supply> supplies) {
        return supplies.stream().filter(s -> s.getQuantity() > 0).sorted(Comparator.comparing(s -> s.getSupplyDate())).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<Supply> supplies = new ArrayList<>();
        List<CollectSupply> collects = new ArrayList<>();
        Supply delivery1 = new Supply("glue", 2.0, LocalDate.of(2020, 10, 20), 10.0);
        Supply delivery2 = new Supply("glue", 3.0, LocalDate.of(2020, 10, 22), 11.0);
        Supply delivery3 = new Supply("tape", 3.0, LocalDate.of(2020, 10, 24), 20.0);
        Supply delivery4 = new Supply("tape", 2.0, LocalDate.of(2020, 10, 25), 18.0);
        supplies.add(delivery1);
        supplies.add(delivery2);
        supplies.add(delivery3);
        supplies.add(delivery4);
        CollectSupply collect1 = new CollectSupply("glue", 3.5, LocalDate.now());
        CollectSupply collect2 = new CollectSupply("glue", 1.2, LocalDate.now());
        CollectSupply collect3 = new CollectSupply("tape", 1.0, LocalDate.now());
        collects.add(collect1);
        collects.add(collect2);
        collects.add(collect3);
        System.out.println(supplies);
        System.out.println(sortedSupplies(supplies));
        System.out.println(collects);


//        collects
//                .forEach(c ->
//                        supplies.stream()
//                                .filter(s -> s.getItem().equals(c.getItem()))
//                                .filter(s -> s.getQuantity() > 0)
//                                .sorted(Comparator.comparing(s -> s.getSupplyDate())).forEach(s -> {
//                            if (s.getQuantity() < c.getQuantity()) {
//                                double q = s.getQuantity();
//                                s.setQuantity(0.0);
//                                c.setQuantity(c.getQuantity() - q);
//                            } else {
//                                s.setQuantity(s.getQuantity() - c.getQuantity());
//                                c.setQuantity(0.0);
//                            }
//                        }));
//
//        System.out.println(supplies);
//        System.out.println(sortedSupplies(supplies));

        // stream only functional
//        Map<String , Double> groupedCollects = collects.stream().collect(groupingBy(CollectSupply::getItem,summingDouble(CollectSupply::getQuantity)));
//
//        System.out.println(groupedCollects);
// Dunno what else to do with that
//        groupedCollects.forEach((str,val) ->
//                supplies.stream().filter(s->s.getItem().equals(str)).sorted(Comparator.comparing(s -> s.getSupplyDate())));
//                supplies.stream()
//                        .sorted(Comparator.comparing(s -> s.getSupplyDate()))
//                        .filter(s-> s.getItem().equals(str))
//                        .forEach(s->));


    }
}
