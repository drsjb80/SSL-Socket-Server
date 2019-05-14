package edu.msudenver;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * @author Joe Prasanna Kumar
 */

public class SSLSocketClient {
    public static void main(String[] args) {
        String strServerName = "localhost";
        int intSSLport = 4443;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out;
        BufferedReader in;

        System.setProperty("javax.net.ssl.trustStore", "testkeystore.ks");
        System.setProperty("javax.net.ssl.trustStorePassword","testpwd");

        SSLSocketFactory sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket sslSocket;
        try {
            sslSocket = (SSLSocket)sslsocketfactory.createSocket(strServerName,intSSLport);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            out = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            out.write("Hello Testing");

            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.write(userInput);
                System.out.println("echo: " + in.readLine());
            }

            out.write(userInput);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            out.close();
            in.close();
            stdIn.close();
            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}