package com.chess.main;

import com.chess.config.AppInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class Main {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AppInjector());
        Server server = injector.getInstance(Server.class);

        new Thread(server, "server").start();
        new Thread(new SocketClientExample(), "1").start();
        new Thread(new SocketClientExample(), "2").start();
    }
}
