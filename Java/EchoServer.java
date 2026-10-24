
import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        String msg = in.readLine();
        System.out.println("Client: " + msg);

        out.println(msg);
        System.out.println("Message sent back to client.");

        s.close();
        ss.close();
    }
}
