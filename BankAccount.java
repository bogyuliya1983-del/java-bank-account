
package Progect.Task5;

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

    // посчитать общую сумму пополнений
    public double calculateTotalDeposit() {
        double totalDeposits = 0.0;
        for (Transaction total : history) {
            if (total.getOperationType().equals("Пополнение")) {
                totalDeposits += total.getAmount();
            }
        }
        return totalDeposits;
    }
    //посчитать общую сумму всех снятий

    public double calculateTotalWithdrawals() {

        double totalWithdrawals = 0.0;

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
            BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt"));

            for (Transaction t : history) {
                writer.write(t.toString());
                writer.newLine();
            }
            writer.close();
            System.out.println("✅ История успешно сохранена в файл history.txt!");

        } catch (IOException e) {
            System.out.println("⚠️ Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
