/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainServer;

import java.net.*;
import java.io.*;

/**
 *
 * @author user
 */
public class Client {

    private KoneksiClient client;

    public void ConnectServer() {
        client = new KoneksiClient();
    }

    public class KoneksiClient {

        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private int playerID;
        private int otherPlayer;

        public KoneksiClient() {
            System.out.println("===CLIENT===");
            try {
                socket = new Socket("localhost", 5000);
                
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                playerID = dis.readInt();
                System.out.println("Connected to server as Player #" + playerID + ".");
            } catch (IOException ex) {
                System.out.println("IOException from KoneksiClient ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Client cl = new Client();
        cl.ConnectServer();
    }
}
