package netolgi.pomodoro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static int test=0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello pomodoro! Напиши пожалуйста команда");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        // Время работы
        int workMin = 25;
        // Время отдыха
        int breakMin = 5;
        // Кол-во повтореий
        int count = 1;
        // Размер брейкбара
        int sizebreak = 30;
        int sizework = 30;
        int help = 0;
        // Переменная для вывода help
        boolean isCallHelp = false;
        // Перебераем введенный массив
        for(int i = 0; i < cmd.length; i++){
            switch(cmd[i]){
                case "-help" ->{
                    printHelpMsg();
                    isCallHelp = true;
                }
                case "-w" ->workMin = Integer.parseInt(cmd[++i]);
                case "-b" ->breakMin = Integer.parseInt(cmd[++i]);
                case "-count" ->count = Integer.parseInt(cmd[++i]);

            }
        }
        if(!isCallHelp) {
            System.out.printf("Параметры программы работаем %d min, отдыхаем %d min," +
                    " кол-во подходов %d\n", workMin, breakMin, count);
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(workMin, breakMin, sizebreak, sizework);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истек: "+ (endTime - startTime)/(1000*60)+"m");
        }
    }

    // Метод таймера
    public static void timer(int work, int breake, int sizebreak, int sizework) throws InterruptedException{

            printProgress("Work Progress::  ", work, sizework);

            printProgress("Break Progress:: ", breake, sizebreak);
    }
    // Метод для ProgressBar
    private static void printProgress(String process, int time, int size) throws InterruptedException {
            int length;
            int rep;
            length = 60 * time / size;
            rep = 60 * time / length;
            int stretchb = size / (3 * time);
            for (int i = 1; i <= rep; i++) {
                double x = i;
                x = 1.0 / 3.0 * x;
                x *= 10;
                x = Math.round(x);
                x /= 10;
                double w = time * stretchb;
                double percent = (x / w) * 1000;
                x /= stretchb;
                x *= 10;
                x = Math.round(x);
                x /= 10;
                percent = Math.round(percent);
                percent /= 10;
                System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
                if (test == 0) {
                    TimeUnit.SECONDS.sleep(length);
                }
            }
            System.out.println();
    }
    // Метод для вывода help
    private static void printHelpMsg() {
        System.out.println("\n\n Pomodoro сделай свое время эфективней\n");
        System.out.println("-w time: время работы, сколько хочешь работать\n");
        System.out.println("-d time: время отдыха, сколько хочешь отдыхать\n");
        System.out.println("-count <count>: количесвто итераций\n");
        System.out.println("--help: меню помощь\n");
    }
}
