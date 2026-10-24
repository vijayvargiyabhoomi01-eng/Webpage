
import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        System.out.println("Connected to server!");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        BufferedReader keyboard = new BufferedReader(
            new InputStreamReader(System.in));

        String msg;

        while (true) {
            System.out.print("Client: ");
            msg = keyboard.readLine();
            out.println(msg);

            if (msg.equalsIgnoreCase("exit"))
                break;

            msg = in.readLine();

            if (msg == null || msg.equalsIgnoreCase("exit"))
                break;

            System.out.println("Server: " + msg);
        }

        s.close();
        System.out.println("Chat ended.");
    }
}
