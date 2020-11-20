import io.reactivex.rxjava3.core.Observable;

public class RxSequence {
  public static void main(String[] args) {
    // cold observable
    Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    observable.subscribe(num -> System.out.println("Subscriber1 " + num), // onNext
      Throwable::printStackTrace, // onError
      () -> System.out.println("Subscriber1 Complete!")); // onCompleted
    // cold observable은 각각의 데이터를 소비한다.
    observable.subscribe(num -> System.out.println("Subscriber2 " + num), // onNext
            Throwable::printStackTrace, // onError
            () -> System.out.println("Subscriber2 Complete!")); // onCompleted
  }
}
