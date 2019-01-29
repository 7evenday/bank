package ftc.shift.sample.models;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private Integer balance;
    private Integer debt;

    public User() {
    }

    public User(String id, String name, Integer balance, Integer debt) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.debt = debt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }

}
