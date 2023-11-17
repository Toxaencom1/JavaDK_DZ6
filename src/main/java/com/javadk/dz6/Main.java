package com.javadk.dz6;

/* Задача:
    В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
    (Monty Hall's Paradox — Википедия) и наглядно убедиться в верности парадокса
    (запустить игру в цикле на 1000 и вывести итоговый счет).
    Необходимо:
    Создать свой Java Maven или Gradle проект;
    Самостоятельно реализовать прикладную задачу;
    Сохранить результат в HashMap<шаг теста, результат>
    Вывести на экран статистику по победам и поражениям
 */

import com.google.common.collect.Maps;
// guava maven addiction
//     <dependency>
//        <groupId>com.google.guava</groupId>
//        <artifactId>guava</artifactId>
//        <version>32.1.3-jre</version>
//     </dependency>

import java.util.Map;
import java.util.Random;

/**
 * The Main class implements the "Monty Hall Paradox" game, demonstrating the paradox that occurs
 * when a player decides to change their choice after the host reveals a door with a goat.
 * <p>
 * Game Rules:
 * - There are three doors. Behind one of them is a car, behind the other two are goats.
 * - The player chooses one door.
 * - The host, knowing where the car is, opens one of the doors with a goat that the player did not choose.
 * - The player can decide to change their choice and select a different door.
 * - The game determines whether the player wins the car after the game concludes.
 * <p>
 * Dependencies:
 * - Utilizes the Guava library (Maps) from com.google.common.collect.Maps for convenient Map operations.
 * <p>
 * Running the Game:
 * - Creates an instance of the Main class, which runs the game 1000 times, calculates the number of wins and losses,
 * and displays the statistics on the screen.
 * <p>
 * Methods:
 * - playGame(): The main method implementing the logic for one round of the game.
 * - revealGoatDoor(int carDoor, int userChoice): Method to reveal the door with a goat by the host.
 * - switchDoor(int userChoice, int revealedGoatDoor): Method to switch the player's choice after the host reveals a door with a goat.
 * <p>
 * Variables:
 * - gameResults: HashMap to store the results of the games (test step, result).
 * - counterWin: Counter for wins.
 * - counterLoose: Counter for losses.
 *
 * @author Anton Takhayev
 * @version 1.0
 */
public class Main {
    public static final Random random = new Random();
    public static final int MAX_GAMES = 1000;
    /**
     * True - "Change" door, when showman asked.
     * False - "Do not change" door, when showman asked.
     */
    public static final boolean GAME_TYPE = true;

    /**
     * The main method of the program. Launches the "Monty Hall Paradox" game 1000 times,
     * prints the results, and displays the statistics of wins and losses.
     *
     * @param args Command line parameters (not used).
     */
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"Парадокс Монти Холла\"!");
        System.out.println("Game type is \"" + GAME_TYPE + "\"");
        Map<Integer, Boolean> gameResults = Maps.newHashMap();
        //Тут я использую подключенную зависимость Guava (Maps) от com.google.common.collect.Maps;
        int counterWin = 0;
        int counterLoose = 0;

        for (int i = 1; i <= MAX_GAMES; i++) {
            boolean endGame = playGame();
            if (endGame) {
                counterWin++;
            } else {
                counterLoose++;
            }
            gameResults.put(i, endGame);
        }
        System.out.println(gameResults);
        System.out.println(counterWin + " Wins");
        System.out.println(counterLoose + " Loses");
    }

    /**
     * The playGame method implements one round of the "Monty Hall Paradox" game.
     *
     * @return true if the player wins, false otherwise.
     */
    private static Boolean playGame() {

//        System.out.println("Есть три двери. За одной из них автомобиль, за двумя другими - козы.");
        int winDoor = random.nextInt(3) + 1; // Рандомно выбираем, за какой дверью автомобиль
//        System.out.println("(" + winDoor + " - это дверь с машиной)");
//        System.out.print("Выберите дверь (1, 2 или 3): ");
        int userChoice = random.nextInt(1, 4);
        int revealedGoatDoor = revealGoatDoor(winDoor, userChoice);
//        System.out.println("Открыли дверь №" + revealedGoatDoor + " с козой");
//        int otherDoor = switchDoor(userChoice, revealedGoatDoor);
//        System.out.println("Перед вами двери: " + otherDoor + ", " + userChoice);
//        System.out.print("Желаете изменить свой выбор? (да/нет): ");

        boolean changeChoice = GAME_TYPE; // random.nextBoolean(); //если случайно то 50 на 50 шансы
        if (changeChoice) {
            userChoice = switchDoor(userChoice, revealedGoatDoor);
//            System.out.println("Вы изменили свой выбор на дверь " + userChoice);
        }
        if (userChoice == winDoor) {
//            System.out.println("Поздравляем! Вы выиграли автомобиль!");
            return true;
        } else {
//            System.out.println("К сожалению, за этой дверью коза. Вы проиграли.");
            return false;
        }
    }

    /**
     * The revealGoatDoor method opens the door with a goat by the host.
     *
     * @param carDoor    The door behind which the car is located.
     * @param userChoice The choice made by the player.
     * @return The number of the door with the revealed goat.
     */
    private static int revealGoatDoor(int carDoor, int userChoice) {
        int revealedGoatDoor;
        do {
            revealedGoatDoor = random.nextInt(3) + 1;
        } while (revealedGoatDoor == carDoor || revealedGoatDoor == userChoice);
        return revealedGoatDoor;
    }

    /**
     * The switchDoor method changes the player's choice after the host reveals a door with a goat.
     *
     * @param userChoice       The current player's choice.
     * @param revealedGoatDoor The door with the revealed goat.
     * @return The new player's choice. -1 = unreachable code
     */
    private static int switchDoor(int userChoice, int revealedGoatDoor) {
        for (int i = 1; i <= 3; i++) {
            if (i != userChoice && i != revealedGoatDoor) {
                return i;
            }
        }
        return -1;
    }
}