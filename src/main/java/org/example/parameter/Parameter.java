package org.example.parameter;

public class Parameter {
    private Integer number_of_philosophers = 5,
                    number_of_fork = 5;
    private Integer time_to_die = 100,
                    time_to_eat = 100,
                    time_to_sleep = 100;
    private Integer number_of_eating = 15;

    @Override
    public String toString() {
        return "Parameter{" +
                "number_of_philosophers=" + number_of_philosophers +
                ", number_of_fork=" + number_of_fork +
                ", time_to_die=" + time_to_die +
                ", time_to_eat=" + time_to_eat +
                ", time_to_sleep=" + time_to_sleep +
                ", number_of_eating=" + number_of_eating +
                '}';
    }

    public Integer getNumber_of_philosophers() {
        return number_of_philosophers;
    }

    public Integer getNumber_of_fork() {
        return number_of_fork;
    }

    public Integer getTime_to_die() {
        return time_to_die;
    }

    public Integer getTime_to_eat() {
        return time_to_eat;
    }

    public Integer getTime_to_sleep() {
        return time_to_sleep;
    }

    public Integer getNumber_of_eating() {
        return number_of_eating;
    }

    public Parameter(String[] args) {
        try {

            this.number_of_philosophers = Integer.parseInt(args[0]);
            this.number_of_fork = Integer.parseInt(args[1]);
            this.time_to_die = Integer.parseInt(args[2]);
            this.time_to_eat = Integer.parseInt(args[3]);
            this.time_to_sleep = Integer.parseInt(args[4]);
            this.number_of_eating = Integer.parseInt(args[5]);
        } catch (NumberFormatException e) {
            System.err.println("Args is not number");
            System.exit(-1);
        }
    }
}
