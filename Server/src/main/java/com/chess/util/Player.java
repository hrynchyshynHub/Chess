package com.chess.util;


import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Player {
    private static final AtomicInteger count = new AtomicInteger(0);
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private Integer id;
    private Color color;

    public Player(String username, String firstName, String secondName, String password){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.id = count.incrementAndGet();
    }

}
