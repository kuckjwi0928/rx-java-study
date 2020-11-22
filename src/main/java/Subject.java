import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class Subject {
  public static void main(String[] args) {
    Observable<Integer> cold = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println("### asyncSubject ###");
    // asyncSubject는 source observable이 완료되었을때 emit 된다. (마지막 값만 방출 시킴)
    AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
    asyncSubject.subscribe(num -> System.out.println("Subscriber1 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber1 Complete!"));
    asyncSubject.subscribe(num -> System.out.println("Subscriber2 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber2 Complete!"));
    cold.subscribe(asyncSubject);

    System.out.println("### behaviorSubject ###");
    // BehaviorSubject로 onNext때 방출된 아이템을 추적 할 수 있다.
    BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.createDefault(-1);
    behaviorSubject.subscribe(num -> System.out.println("Subscriber1 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber1 Complete!"));
    behaviorSubject.subscribe(num -> System.out.println("Subscriber2 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber2 Complete!"));
    cold.subscribe(behaviorSubject);

    System.out.println("### PublishSubject ###");
    // PublishSubject은 기본값을 지정 할 수 없다. 또한 구독 후 배출되는 항목만? 배출한다.
    PublishSubject<Integer> publishSubject = PublishSubject.create();
    publishSubject.subscribe(num -> System.out.println("Subscriber1 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber1 Complete!"));
    publishSubject.subscribe(num -> System.out.println("Subscriber2 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber2 Complete!"));
    cold.subscribe(publishSubject);

    System.out.println("### ReplySubject ###");
    // ReplySubject은 구독 시점에 상관없이 모든 아이템을 방출한다.
    ReplaySubject<Integer> replaySubject = ReplaySubject.create();
    replaySubject.subscribe(num -> System.out.println("Subscriber1 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber1 Complete!"));
    replaySubject.subscribe(num -> System.out.println("Subscriber2 " + num),
            Throwable::printStackTrace,
            () -> System.out.println("Subscriber2 Complete!"));
    cold.subscribe(replaySubject);
  }
}
