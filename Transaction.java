package Progect.Task5;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String operationType;
    private double amount;
    private double afterBalanc;
    private LocalDateTime time;

public Transaction(String operationType,double amount,double afterBalanc){
    this. operationType = operationType;
    this.amount = amount;
    this.afterBalanc = afterBalanc;
    this.time = LocalDateTime.now();
}

    public String getOperationType() {
        return operationType;
    }

    public double getAmount() {
        return amount;
    }

    public String toString (){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    return "["+time.format(formatter) + "]" + operationType +" Сумма " + amount + " (Баланс: " + afterBalanc + ")" ;
}
}
