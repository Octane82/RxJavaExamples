package exam1;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Планировщики - позволяют переключать потоки
 *
 * В RxJava можно легко указать, в каком потоке должны запускаться ваши Observer и Subscriber,
 * воспользовавшись, соответственно, subscribeOn() и observeOn():
 */
public class SchedulersEx5 {

    public static void main(String[] args) {
        new SchedulersEx5().exampleOne();
    }




    private void exampleOne() {

        // В Android указываем в каком потоке запускается Observer и Subscriber
        /*myObservableServices.retrieveImage(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> myImageView.setImageBitmap(bitmap));*/


        // subscribeOn() - указывает в каком потоке выполнять цепочку операций сверху от subscribeOn()
        // observeOn() - указывает в каком потоке выполнять цепочку операций снизу от observeOn()


        // ПОДПИСКИ
        Subscription subscription = Observable.just("Hello, World!")
                .subscribe(s -> System.out.println(s));

        // Отписаться !
        subscription.unsubscribe();
        System.out.println("Unsubscribed=" + subscription.isUnsubscribed());

        // Также существует объединение подписок
        // через которую впоследствии вы будете сохранять все ваши подписки, и которая будет автоматически очищаться
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription);

        /**
         * Внимание! Как только вы вызвали CompositeSubscription.unsubscribe(),
         * этот экземпляр CompositeSubscription перестанет быть доступным для использования (
         * то есть добавлять к нему подписки вы, конечно,
         * сможете, но он будет тут же автоматом вызывать на них unsubscribe())!
         * Если вы хотите в дальнейшем продолжать использовать CompositeSubscription,
         * вам придётся создать новый экземпляр.
         */
        compositeSubscription.unsubscribe();

    }


    // https://habrahabr.ru/post/265997/ - GROKAEM Rx 4  - pro android ! (rxBindings - биндит UI в андроиде на Rx)

    /**
     * ПРЕОБРАЗОВАНИЕ СТАРОГО МЕТОДА В Observable
     *
     * достаточно использовать Observable.just() и Observable.from():
     */

    private Object oldMethod() {
        return new Object();
    }

    public Observable<Object> newMethod() {
        return Observable.just(oldMethod());
    }


}
