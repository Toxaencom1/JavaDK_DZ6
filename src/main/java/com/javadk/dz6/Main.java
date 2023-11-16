package com.javadk.dz6;

/*
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
(Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса
(запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям

автомобиль равновероятно размещён за любой из трёх дверей;
ведущий знает, где находится автомобиль;
ведущий в любом случае обязан открыть дверь с козой (но не ту, которую выбрал игрок) и предложить игроку изменить выбор;
если у ведущего есть выбор, какую из двух дверей открыть (то есть, игрок указал на верную дверь,
и за обеими оставшимися дверями — козы), он выбирает любую из них с одинаковой вероятностью
 */

import com.google.common.collect.Maps;
// зависимость Guava Maven
//     <dependency>
//        <groupId>com.google.guava</groupId>
//        <artifactId>guava</artifactId>
//        <version>32.1.3-jre</version>
//     </dependency>

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"Парадокс Монти Холла\"!");

        Map<Integer, Boolean> gameResults = Maps.newHashMap();
        //Тут я использую подключенную зависимость Guava (Maps) от com.google.common.collect.Maps;
        int counterWin = 0;
        int counterLoose = 0;

        for (int i = 1; i <= 1000; i++) {
            boolean endGame = playGame();
            if (endGame) { counterWin++;}
            else {counterLoose++;}
            gameResults.put(i, endGame);
        }
        System.out.println(gameResults);
        System.out.println(counterWin + " Wins");
        System.out.println(counterLoose + " Loses");
    }

    private static Boolean playGame() {
        Random random = new Random();
//        System.out.println("Есть три двери. За одной из них автомобиль, за двумя другими - козы.");
        int carDoor = random.nextInt(3) + 1; // Рандомно выбираем, за какой дверью автомобиль
//        System.out.println("(" + carDoor + " - это дверь с машиной)");
//        System.out.print("Выберите дверь (1, 2 или 3): ");
        int userChoice = random.nextInt(1, 4);
        int revealedGoatDoor = revealGoatDoor(carDoor, userChoice);
//        System.out.println("Открыли дверь №" + revealedGoatDoor + " с козой");
//        int otherDoor = switchDoor(userChoice, revealedGoatDoor);
//        System.out.println("Перед вами двери: " + otherDoor + ", " + userChoice);
//        System.out.print("Желаете изменить свой выбор? (да/нет): ");

        boolean changeChoice = true; //шанс выиграть 2/3 а при // random.nextBoolean(); если случайно то 50 на 50 шансы
        if (changeChoice) {
            userChoice = switchDoor(userChoice, revealedGoatDoor);
//            System.out.println("Вы изменили свой выбор на дверь " + userChoice);
        }
        if (userChoice == carDoor) {
//            System.out.println("Поздравляем! Вы выиграли автомобиль!");
            return true;
        } else {
//            System.out.println("К сожалению, за этой дверью коза. Вы проиграли.");
            return false;
        }
    }

    private static int revealGoatDoor(int carDoor, int userChoice) {
        Random random = new Random();
        int revealedGoatDoor;
        do {
            revealedGoatDoor = random.nextInt(3) + 1;
        } while (revealedGoatDoor == carDoor || revealedGoatDoor == userChoice);
        return revealedGoatDoor;
    }

    // Метод для смены выбора пользователя
    private static int switchDoor(int userChoice, int revealedGoatDoor) {
        for (int i = 1; i <= 3; i++) {
            if (i != userChoice && i != revealedGoatDoor) {
                return i;
            }
        }
        return -1;
    }
}