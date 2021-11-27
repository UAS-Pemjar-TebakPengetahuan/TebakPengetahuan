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
public class Server {

    private ServerSocket ss;
    private int numPlayer;
    private KoneksiServer player1;
    private KoneksiServer player2;
    private KoneksiServer player3;

    public Server() {
        System.out.println("===GAME SERVER===");
        numPlayer = 0;

        try {
            ss = new ServerSocket(5000);
        } catch (IOException ex) {
            System.out.println("IOException from GameServer");
        }
    }

    public void Accept() {
        try {
            System.out.println("Waiting for Connection....");
            while (numPlayer < 3) {
                Socket s = ss.accept();
                numPlayer++;
                System.out.println("Player #" + numPlayer + " has connected.");
                KoneksiServer server = new KoneksiServer(s,numPlayer);
                if(numPlayer==1){
                    player1 = server;
                }else if(numPlayer==2){
                    player2 = server;
                }else{
                    player3 = server;
                }
                Thread t = new Thread( server);
                t.start();
                
            }
            System.out.println("We have 3 Player. No longer accepting connections");
        } catch (IOException ex) {
            System.out.println("IOException from Accept");
        }
    }

    private class KoneksiServer implements Runnable{
    private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private int playerID;
        public KoneksiServer(Socket s, int id) {
            socket = s;
            playerID = id;
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

            } catch (IOException ex) {
                System.out.println("IOException from SSC Constructor");
            }
        }
        
        public void run(){
            try{
                dos.writeInt(playerID);
                dos.flush();
                
                while(true){
                    
                }
            }
            catch(IOException ex){
                System.out.println("IOException from run() SSC");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Server game = new Server();
        game.Accept();
    }
}
