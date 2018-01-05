package com.chess.main;

import com.chess.saver.GameSaver;
import com.chess.model.GameBoard;
import com.chess.util.Color;
import com.chess.util.DataTransferObject;
import com.chess.util.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private Selector selector;
    private static final int BUFFER_SIZE = 1024;
    private Map<SocketChannel, List<byte[]>> dataMap;
    private InetSocketAddress hostIP;
    @Inject
    private GameSaver saver;
    @Inject
    private GameBoard board;

    public Server() {
        this.hostIP = new InetSocketAddress("localhost", 9999);
        dataMap = new HashMap<>();
    }

    public Server(GameBoard board) {
        this.board = board;
        this.hostIP = new InetSocketAddress("localhost", 9999);
        dataMap = new HashMap<>();
    }

    public Server(String address, int port) {
        this.hostIP = new InetSocketAddress(address, port);
        dataMap = new HashMap<>();
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    @Override
    public void run() {
        try{
            initServer();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void initServer() {
        initBoard();
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
                    }else if(key.isWritable()){
                        this.write(key);
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
        dataMap.put(channel, new ArrayList<byte[]>());
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int numRead = -1;
        numRead = channel.read(buffer);
        if (numRead == -1) {
            this.dataMap.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            channel.close();
            key.cancel();
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);

        DataTransferObject dt = objectMapper.readValue(data, DataTransferObject.class);

        dt.setStatus(Status.OK);
        byte [] messageFromServer = objectMapper.writeValueAsBytes(dt);

        if(isValidMove(dt)){
            broadcastMode(key, messageFromServer);
            // TODO: write to all;

        }else{
            // TODO: return status error only for one client;
        }

        System.out.println(dt);

    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.dataMap.get(channel);
        Iterator<byte[]> items = pendingData.iterator();
        while (items.hasNext()) {
            byte[] item = items.next();
            items.remove();
            channel.write(ByteBuffer.wrap(item));
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    private void broadcastMode(SelectionKey key, byte[] data) {
//        SocketChannel channel = (SocketChannel) key.channel();
//        List<byte[]> pendingData = this.dataMap.get(channel);
//        pendingData.add(data);
        for(Map.Entry<SocketChannel, List<byte[]>> entry : dataMap.entrySet()){
            entry.getValue().add(data);
        }
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private boolean isValidMove(DataTransferObject dt){
        return board.move(dt.getMove());
    }

    private void initBoard(){
        board.initializeBoard();
        board.initializatePieces(Color.WHITE);
        board.initializatePieces(Color.BLACK);
        board.showBoard();
    }

}
