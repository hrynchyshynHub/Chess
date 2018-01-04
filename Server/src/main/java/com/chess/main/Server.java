package com.chess.main;

//import com.chess.model.Board;
//import com.chess.saver.GameSaver;
import com.google.inject.Inject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */
public class Server implements Runnable{
//    private GameSaver saver;
    private Selector selector;
    private static final int BUFFER_SIZE = 1024;
    private Map<SocketChannel, List> dataMapper;
    private InetSocketAddress hostIP;


    public Server(String address, int port) {
        this.hostIP = new InetSocketAddress(address, port);
        dataMapper = new HashMap<>();
    }

    @Override
    public void run() {
        initServer();
    }

    public void initServer() {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(hostIP);
            serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started...");

            while (!Thread.currentThread().isInterrupted()) {
                this.selector.select();
                Iterator keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = (SelectionKey) keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        this.accept(key);
                    } else if (key.isReadable()) {
                        this.read(key);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteAddr);
        dataMapper.put(channel, new ArrayList());
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int numRead = -1;
        numRead = channel.read(buffer);
        if (numRead == -1) {
            this.dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            channel.close();
            key.cancel();
            return;
        }
        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        System.out.println("Got: " + new String(data));
    }

//    @Inject
//    public void setSaver(GameSaver saver) {
//        this.saver = saver;
//    }
//
//    public void saveGame(Board board) {
//        saver.save(board);
//    }
}
