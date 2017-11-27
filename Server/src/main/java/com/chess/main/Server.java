package com.chess.main;

import com.chess.model.Board;
import com.chess.model.pieces.Bishop;
import com.chess.model.pieces.Piece;
import com.chess.util.Color;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */
public class
Server extends Thread{
     Socket socket;
     int connectionCount = 0;
     private static  final Logger logger = Logger.getLogger(Server.class);

    public Server(Socket socket, int connectionCount){
        this.socket = socket;
        this.connectionCount = connectionCount ;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {
            byte buf[] = new byte[64 * 1024];
            int r = is.read(buf);
            String data = new String(buf, 0, r);
            data = "" + connectionCount + ": " + "\n" + data;
            os.write(data.getBytes());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5556, 0 , InetAddress.getByName("localhost"));){
            int i = 0;
            while (true){
                new Server(serverSocket.accept(), i);
                i++;
            }
        } catch (IOException e) {
           logger.error(e);
        }

    }


}
