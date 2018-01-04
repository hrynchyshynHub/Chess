package com.chess.main;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

//        Injector injector = Guice.createInjector(new AppInjector()); for DI container

//        Server server = new Server("localhost", 9999);
//        server.initServer();

        new Thread(new Server("localhost", 9999), "server").start();
        new Thread(new SocketClientExample(), "1").start();
        new Thread(new SocketClientExample(), "2").start();
    }
}
