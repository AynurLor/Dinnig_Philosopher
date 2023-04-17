package org.example.philosopher;


import org.example.parametr.Parameter;

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

    private Parameter parameter_philosopher;

    public Philosopher(Parameter parameter, Object left_fork, Object right_fork) {
        parameter_philosopher = parameter;
        this.left_fork = left_fork;
        this.right_fork = right_fork;
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
                synchronized (left_fork) {
                    doAction("Take left fork");
                    synchronized (right_fork) {
                        doAction("Take right fork");
                        doAction("Eating");
                        doAction("Put right fork");
                    }
                    doAction("Put left fork");
                    doAction("Sleep");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }
}
