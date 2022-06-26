package Jyq;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    private ServerSocket serverSocket;
    private static String name;
    private static String IP;
    private static String LoginTime;
    private static String Time;
    public Sever(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

    }
    public void StartSever() {
         BufferedReader bufferedReader;
        try {
                Socket socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                name = bufferedReader.readLine();
                LoginTime = bufferedReader.readLine();
                IP = bufferedReader.readLine();
                System.out.println("Name:" + name);
                System.out.println("LoginTime:" + LoginTime);
                System.out.println("IP:" + IP);
                Time = bufferedReader.readLine();
                System.out.println("GamingTime:" + Time);
            (LazyUtils.ReadObject(Directories.RankingList, RankingList.class)).addRank(new Rank(IP, Integer.parseInt(Time), name,LoginTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10024);
        Sever sever = new Sever(serverSocket);
        sever.StartSever();
    }
}
