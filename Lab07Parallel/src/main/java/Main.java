import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Observable<String> o = Observable.just("Hello in observable");
        o.subscribe(s->System.out.println(s));

        Integer[] intArr = {0,1,2,3,4,5,6,7,8,9};
        Integer[] intArr2 = {100,200,300,400,500,600,700,800,900};
        Observable<Integer> o2 = Observable.from(intArr);
        Observable<Integer> o3 = Observable.from(intArr2);
        o2.subscribe(i->System.out.println(i));
        o3.subscribe(i->System.out.println(i));

        Observable.merge(o2,o3).subscribe(i->System.out.println(i));

        List<Integer> zippedIntArr = new ArrayList<>();
        Observable.zip(o2,o3,(i1,i2)->i1+i2).subscribe(zippedIntArr::add);
        zippedIntArr.forEach(System.out::println);

        o2.all(n->n>5).subscribe(s-> System.out.println(s));
    }
}
