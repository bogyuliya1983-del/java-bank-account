

package myLife.Task5;

import java.util.InputMismatchException;
import java.util.Scanner;


public class BankApp {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Анна", 1000.0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать");
        System.out.println("Баланс составляет :" + bankAccount.getBalance());

        while (true) {
            try {
                System.out.println("Меню:");
                System.out.println("1 - Пополнить счет");
                System.out.println("2 - Снять деньги");
                System.out.println("3 - Показать историю");
                System.out.println("4 - Вишенка на торт (Всего пополнено)");
                System.out.println("5 - Вишенка на торт (Всего снято)");
                System.out.println("6 - Сохранить историю в файл");
                System.out.println("0 - Выход");
                System.out.print("Ваш выбор: ");


                int chois = scanner.nextInt();//выбор
                if (chois == 1) {
                    System.out.println("Введите сумму для пополнения: ");
                    double amount = scanner.nextDouble();
                    bankAccount.deposit(amount);

                } else if (chois == 2) {
                    System.out.println("Введите сумму для снятия: ");
                    double amount = scanner.nextDouble();
                    bankAccount.withdraw(amount);

                } else if (chois == 3) {
                    for (Transaction records : bankAccount.getHistory()) {
                        System.out.println(records);
                    }
                } else if (chois == 0) {
                    break;

                } else if (chois == 4) {
                    System.out.println("всего  пополнений на: " + bankAccount.calculateTotalDeposit() + "руб");

                } else if (chois == 5) {
                    System.out.println("всего  снято: " + bankAccount.calculateTotalWithdrawals() + "руб");
                }
                else if (chois == 6) {
                bankAccount.saveHistoryToFile();
                }
            }catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно вводить только цифры! Попробуйте еще раз.\n");
                scanner.nextLine();
            }


            //bankAccount.withdraw(2000);
            //bankAccount.withdraw(300);
            //System.out.println("Баланс: " + bankAccount.getBalance() + " руб.");
            //System.out.println("ИСТОРИЯ ОПЕРАЦИЙ: ");


        }
        scanner.close();
    }
}