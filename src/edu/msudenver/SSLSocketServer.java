package edu.msudenver;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

/**
 * @author Joe Prasanna Kumar
 */


public class SSLSocketServer {
    public static void main(String[] args) {
        int intSSLport = 4443;

        System.setProperty("javax.net.ssl.keyStore","testkeystore.ks");
        System.setProperty("javax.net.ssl.keyStorePassword","testpwd");

        // System.setProperty("javax.net.debug","all");

        SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket;
        SSLSocket sslSocket;

        try {
            sslServerSocket = (SSLServerSocket) sslServerSocketfactory.createServerSocket(intSSLport);
            sslSocket = (SSLSocket) sslServerSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            BufferedWriter out;
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            String inputLine;

            try {
                while ((inputLine = in.readLine()) != null) {
                    out.write(inputLine);
                    System.out.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            try {
                in.close();
                out.close();
                sslSocket.close();
                sslServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}