package org.example.philosopher;

import org.example.parameter.Parameter;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Класс философ реализует интерфейс Runnable, имеет поля левая и правая вилка,
 * методы для эмитации процесса еды и раздумья
 * В качкестве аргумеетов конструтора принимает:
 * - Класс Parameter с параметрами философа
 * - Обьект захвата левой и правой филки
 */
public class Philosopher implements Runnable{
    private Object left_fork;
    private Object right_fork;
    private volatile long time_hunger;
    private AtomicInteger counter;

    private Parameter parameter_philosopher;

    public Philosopher(Parameter parameter, Object left_fork, Object right_fork) {
        parameter_philosopher = parameter;
        this.left_fork = left_fork;
        this.right_fork = right_fork;
        counter = new AtomicInteger(parameter_philosopher.getNumber_of_eating());
    }


    private void doAction(String action) throws InterruptedException {
        String line_action = System.currentTimeMillis() +
                " " + Thread.currentThread().getName() +
                " " + action;
        if (action.equals("Thinking")) {
            System.out.println(line_action);
            Thread.sleep((int) Math.random() * 100);
        } else if (action.equals("Eating")) {
            System.out.println(line_action);
            Thread.sleep(parameter_philosopher.getTime_to_eat());
        } else if (action.equals("Sleep")) {
            System.out.println(line_action);
            Thread.sleep(parameter_philosopher.getTime_to_sleep());
        } else {
            System.out.println(line_action);
            Thread.sleep((int) Math.random() * 100);
        }
    }

    // в процессе выполнения
    public void run() {
        try {
            while (true) {
                doAction("Thinking");
                time_hunger = System.currentTimeMillis();
                synchronized (left_fork) {
                    doAction("Take left fork");
                    synchronized (right_fork) {
                        doAction("Take right fork");
                        check_hunger();
                        doAction("Eating");
                        doAction("Put right fork");
                    }
                    doAction("Put left fork");
                    doAction("Sleep");
                }
                if (counter.decrementAndGet() == 0) {
                    System.out.println(System.currentTimeMillis() + " " +
                                       Thread.currentThread().getName() +
                                       " finish");
                    return;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }
    // проверка того, сколько времени философ провел без еды
    private void check_hunger() {
        if (System.currentTimeMillis() - time_hunger >
                parameter_philosopher.getTime_to_die()) {
            System.out.println(System.currentTimeMillis());
            System.out.println(time_hunger);
            System.out.println();
            System.err.println(Thread.currentThread().getName() + " is dead");
            System.exit(-1);
        }
    }
}
