
import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader keyboard = new BufferedReader(
            new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        System.out.print("Enter a message: ");
        String msg = keyboard.readLine();

        out.println(msg);

        String reply = in.readLine();
        System.out.println("Echo from server: " + reply);

        s.close();
    }
}
