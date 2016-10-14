package exam1;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Обработка ошибок
 */
public class ErrorHandlingEx4 {

    public static void main(String[] args) {
        new ErrorHandlingEx4().sampleOne();
    }


    /**
     * Error handling
     */
    private void sampleOne() {

        //Observable.just("Hello world").map(s -> potentialException()).subscribe();


        // Unchecked exception
        Observable.just("Hello!")
                .map(input -> { throw new RuntimeException(); })
                .subscribe(
                        System.out::println,
                        error -> System.out.println("Error!")
                );


        // Checked exception
        Observable.just("Hello world").map(input -> {
            try {
                return otherPotentialException(input);
            } catch (IOException e) {

                // Удобный метод бросить RuntimeException ошибку прямо или обернуть любой другой тип исключения в RuntimeException.
                //throw Exceptions.propagate(e);              // Распространить исключение дальше

                // Или без RuntimeExceptions
                return Observable.error(e);     // В этом случае не надо вызывать onError()
            }
        }).subscribe(s -> System.out.println(s));

        // Checked exceptions
        /*Observable.just("Hello!")
                .map(input -> {
                    try {
                        return otherPotentialException(input);
                    } catch (Throwable t) {
                        throw Exceptions.propagate(t);
                    }
                }).subscribe(s -> System.out.print(s));*/



        Observable.interval(1, TimeUnit.SECONDS)
                .map(input -> { throw new RuntimeException(); })
                .onErrorReturn(error -> "Uh oh")
                .subscribe(System.out::println);


    }




    private void potentialException() throws SomeException {
        throw new SomeException();
    }


    private String otherPotentialException(String input) throws IOException {
        //return "yay";
        throw  new IOException();
    }


    private void dangerousOperation() throws RuntimeException {

    }


}
