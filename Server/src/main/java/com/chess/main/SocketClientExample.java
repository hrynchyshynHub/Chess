package com.chess.main;

import com.chess.model.Cell;
import com.chess.util.DataTransferObject;
import com.chess.util.Move;
import com.chess.util.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientExample implements Runnable{

    public void startClient() throws IOException, InterruptedException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 9999);
        SocketChannel client = SocketChannel.open(hostAddress);
        System.out.println("Client... started");
        String threadName = Thread.currentThread().getName();
        // Send messages to server

        Move move = new Move(new Cell("2d"), new Cell("3d"));
        Move move2 = new Move(new Cell("7d"), new Cell("6d"));

        Player p1 = new Player("Ivan");
        Player p2 = new Player("Pavlo");

        DataTransferObject dataTransferObject = new DataTransferObject(move, p1);

        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < 3; i++) {
            byte [] message = objectMapper.writeValueAsBytes(dataTransferObject);
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println("Data sends");
            buffer.clear();
            Thread.sleep(5000);
        }
        client.close();
    }

    @Override
    public void run() {
        try {
            new SocketClientExample().startClient();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
