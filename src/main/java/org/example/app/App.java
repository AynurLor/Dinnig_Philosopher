package org.example.app;

import org.example.parameter.Parameter;
import org.example.philosopher.Philosopher;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * В качкестве аргумеетов приложение принимает:
 * - кол-во философов и вилок,
 * - время сколько филосов может обхадиться без еды в миллисек.,
 * - продолжитлеьность приема пищи в миллисек.,
 * - макс кол-во обедов для философа
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Parameter parameter = new Parameter(args);
        Integer count_philosophers = parameter.getNumber_of_philosophers();

        if (count_philosophers < 1) System.exit(-1);

        starting_threads(parameter);
    }

    // Распаралеливание потоков - сначало запускаются четные, далее нечетные
    public static void starting_threads(Parameter parameter) {
        Philosopher[] philosophers = new Philosopher[parameter.getNumber_of_philosophers()];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) forks[i] = new Object();

        for (int i = 0; i < philosophers.length; i++) {
            Object left_fork = forks[i];
            Object right_fork = forks[(i+1) % forks.length];
            if (i == philosophers.length -1) {
                philosophers[i] = new Philosopher(new Parameter(parameter), right_fork, left_fork);
            }else {
                philosophers[i] = new Philosopher(new Parameter(parameter), left_fork, right_fork);
            }
        }
        distribution_thread(philosophers);

    }
    public static void distribution_thread(Philosopher[] philosophers) {
        for (int j = 1; j < 2; j++) {
            for (int i = 0; i < philosophers.length; i++) {
                if (j == 1 && i % 2 == 0) {
                    new Thread(philosophers[i], String.valueOf(i+1)).start();
                } else if (j == 2 && i % 2 != 0) {
                    new Thread(philosophers[i], String.valueOf(i+1)).start();
                }
            }
        }
    }
}
