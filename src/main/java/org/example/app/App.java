package org.example.app;

import org.example.parameter.Parameter;
import org.example.philosopher.Philosopher;

/**
 * В качкестве аргумеетов приложение принимает:
 * - кол-во философов и вилок,
 * - время сколько филосов может обхадиться без еды в миллисек.,
 * - продолжитлеьность приема пищи в миллисек.,
 * - макс кол-во обедов для философа
 */
public class App 
{
    public static void main( String[] args )
    {
        Parameter parameter = new Parameter(args);
        Integer count_thread = parameter.getNumber_of_philosophers();

        if (count_thread < 1) System.exit(-1);

        Philosopher[] philosophers = new Philosopher[count_thread];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object left_fork = forks[i];
            Object right_fork = forks[(i+1) % forks.length];
            if (i == philosophers.length -1) {
                philosophers[i] = new Philosopher(new Parameter(args), right_fork, left_fork);
            }else {
                philosophers[i] = new Philosopher(new Parameter(args), left_fork, right_fork);
            }
            Thread thread = new Thread(philosophers[i], String.valueOf(i+1));
            thread.start();
        }

    }
}
