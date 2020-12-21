import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Observable<String> o = Observable.just("Hello in observable");
        o.subscribe(s->System.out.println(s));

        Integer[] intArr = {0,1,2,3,4,5,6,7,7,7,9};
        Integer[] intArr2 = {100,200,300,400,500,600,700,800,900,901};
        String[] strArr = {"Ala","ma","kota","a","kot","ma","Ale"};
        Observable<Integer> o2 = Observable.from(intArr);
        Observable<Integer> o3 = Observable.from(intArr2);
        Observable<String> o4 = Observable.from(strArr);
        Observable<Object> o5 = Observable.empty();
        o2.subscribe(i->System.out.println(i));
        o3.subscribe(i->System.out.println(i));
        o4.subscribe(s->System.out.println(s));

        Observable.merge(o2,o3).subscribe(i->System.out.println(i));
        Observable.merge(o2,o4).subscribe(i->System.out.println(i));

        List<Integer> zippedIntArr = new ArrayList<>();
        Observable.zip(o2,o3,(i1,i2)->i1+i2).subscribe(zippedIntArr::add);
        zippedIntArr.forEach(System.out::println);

        o2.all(n->n>5).subscribe(s-> System.out.println("all items in o2 less than 5: "+s));
        o3.all(i -> i>99).subscribe(s-> System.out.println("all items in o3 greater than 99: "+s));

        Observable.sequenceEqual(o2,o3).subscribe(b->System.out.println("o2 and o3 are equal sequences: "+b));

        o4.count().subscribe(s->System.out.println(s));

        o4.elementAt(2).subscribe(s->System.out.println(s));

        o5.contains("Ala").subscribe(s->System.out.println(s));

        o5.isEmpty().subscribe(s->System.out.println(s));

        o2.distinct().subscribe(i->System.out.println(i));

        o2.distinctUntilChanged().subscribe(i->System.out.println(i));

        o4.skipWhile(s->!"kota".equals(s)).subscribe(i->System.out.println(i));
    }
}
