package com.chess.util;



import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */

public class Player implements Serializable{
    private static final AtomicInteger count = new AtomicInteger(0);
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private Integer id;
    private Color color;

    public Player() {
    }

    public Player(String username, String firstName, String secondName, String password){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.id = count.incrementAndGet();
    }

    public Player(String username) {
        this.username = username;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
