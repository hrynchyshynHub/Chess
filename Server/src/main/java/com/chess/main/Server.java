package com.chess.main;

import org.apache.log4j.Logger;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ivan.hrynchyshyn on 15.11.2017.
 */
public class Server implements Runnable{
     private static ServerSocket socket;
     private Socket fistPlayerSocket;
     private Socket secondPlayerSocket;
     private ObjectInputStream ois;
     private ObjectOutputStream oos;
     private AtomicInteger connectionCount = new AtomicInteger();
     private static final Logger logger = Logger.getLogger(Server.class);

    public Server(){
        try {
            socket = new ServerSocket(5556, 0, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true){
            try{
                fistPlayerSocket = socket.accept();
                secondPlayerSocket = socket.accept();
//                ois = new ObjectInputStream(.getInputStream());
//                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                connectionCount.incrementAndGet();
                System.out.println("Connection: " + connectionCount);
                System.out.println(fistPlayerSocket.getInputStream().read());
            }catch (IOException e){
                   e.printStackTrace();
            }

        }
    }

}
