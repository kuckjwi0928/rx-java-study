import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class RxSequence {
  public static void main(String[] args) {
    // cold observable
    System.out.println("### cold observable ###");
    Observable<Integer> cold = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    cold.subscribe(num -> System.out.println("Subscriber1 " + num), // onNext
      Throwable::printStackTrace, // onError
      () -> System.out.println("Subscriber1 Complete!")); // onCompleted
    // cold observable은 다른 subscription을 기다린다.
    cold.subscribe(num -> System.out.println("Subscriber2 " + num), // onNext
            Throwable::printStackTrace, // onError
            () -> System.out.println("Subscriber2 Complete!")); // onCompleted

    // hot observable
    System.out.println("### hot observable ###");
    ConnectableObservable<Integer> hot = cold.publish();
    // hot observable은 다른 subscription을 기다리지않고 동시에 실행한다.
    hot.subscribe(num -> System.out.println("Subscriber1 " + num), // onNext
            Throwable::printStackTrace, // onError
            () -> System.out.println("Subscriber1 Complete!")); // onCompleted
    hot.subscribe(num -> System.out.println("Subscriber2 " + num), // onNext
            Throwable::printStackTrace, // onError
            () -> System.out.println("Subscriber2 Complete!")); // onCompleted
    hot.connect();
  }
}
