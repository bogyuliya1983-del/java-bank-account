
//№1: Банковский счет (BankAccount)
//Часть 1. Создайте класс BankAccount
//В нем должны быть:
//Два приватных (private) поля:
//ownerName (тип String) — имя владельца.
//balance (тип double) — текущий баланс.
//Конструктор, который принимает имя и начальный баланс, и присваивает их полям (используйте ключевое слово this).
//Публичный метод deposit(double amount) (положить деньги): он должен увеличивать баланс. Дополнительно:
// добавьте проверку, что сумма больше 0.
//Публичный метод withdraw(double amount) (снять деньги): он должен уменьшать баланс. Важно: добавьте проверку,
// что сумма больше 0 и что на счете достаточно денег. Если денег не хватает, выведите в консоль сообщение
// "Недостаточно средств" и не меняйте баланс.
//Публичный метод getBalance() (геттер), который просто возвращает текущий баланс.
//Публичный метод getOwnerName() (геттер), который возвращает имя владельца.

//Часть 2. Создайте класс Main
//В нем должен быть метод public static void main(String[] args). Внутри этого метода нужно:
//Создать объект BankAccount для владельца "Анна" с начальным балансом 1000.0.
//Вывести на экран приветствие и начальный баланс (используя геттеры).
//Положить на счет 500.0 (вызвать метод deposit). Вывести новый баланс.
//Попробовать снять 2000.0 (вызвать метод withdraw). В консоли должно появиться сообщение "Недостаточно средств".
//Попробовать снять 300.0 (вызвать метод withdraw). Вывести итоговый баланс.

//

//История транзакций
/*научим BankAccount сохранять историю операций в текстовый файл history.txt.*/
package myLife.Task5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String ownerName;
    private double balance;

    private List<Transaction> history = new ArrayList<>();


    public BankAccount(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
        this.history.add(new Transaction("Открытие счета", 0.0, balance));

    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Введите сумму пополнения больше 0");
            return;
        }
        balance += amount;
        this.history.add(new Transaction("Пополнение", amount, balance));
        System.out.println("Успешное пополнение на сумму " + amount);
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Введите сумму снятия больше 0");
            return;
        } else if (amount > balance) {
            System.out.println("Недостаточно средств");
            return;
        }
        balance -= amount;
        this.history.add(new Transaction("Снятие", amount, balance));
        System.out.println("успешное снятие " + amount);

    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    // Вишенка на торт: считаем общую сумму пополнений
    public double calculateTotalDeposit() {
        double totalDeposits = 0.0;
        for (Transaction total : history) {
            if (total.getOperationType().equals("Пополнение")) {
                totalDeposits += total.getAmount();
            }
        }
        return totalDeposits;

    }
    //Напиши метод, который считает общую сумму всех снятий


    public double calculateTotalWithdrawals() {
        //  // Шаг 1: Создаем переменную-счетчик, начинаем с нуля
        double totalWithdrawals = 0.0;
        // // Шаг 2: Запускаем цикл, который пройдет по каждой записи в истории
        for (Transaction totalw : history) {
            if (totalw.getOperationType().equals("Снятие")) {
                totalWithdrawals += totalw.getAmount();
            }
        }
return totalWithdrawals;
    }

    //  Сохраняем историю в текстовый файл
    public void saveHistoryToFile() {
        try {
            // Создаем поток для записи в файл "history.txt" в папке проекта
            BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt"));


            // Проходим по всей истории и записываем каждую строку в файл
            for (Transaction t : history) {
                writer.write(t.toString()); // Используем твой красивый toString() из класса Transaction!
                writer.newLine();           // Делаем перенос строки после каждой записи
            }

            // Обязательно закрываем файл, чтобы данные физически записались на диск
            writer.close();
            System.out.println("✅ История успешно сохранена в файл history.txt!");

        } catch (IOException e) {
            System.out.println("⚠️ Ошибка при сохранении файла: " + e.getMessage());
        }
    }

}
