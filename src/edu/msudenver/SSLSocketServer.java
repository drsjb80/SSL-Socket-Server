package edu.msudenver;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

/**
 * @author Joe Prasanna Kumar
 */


public class SSLSocketServer {
    public static void main(String[] args) throws IOException {
        int intSSLport = 443;

        System.setProperty("javax.net.ssl.keyStore","testkeystore.ks");
        System.setProperty("javax.net.ssl.keyStorePassword","testpwd");
        // System.setProperty("javax.net.debug","all");

        SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketfactory.createServerSocket(intSSLport);
        SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

        String inputLine = in.readLine();
        System.out.println("RECEIVED: " + inputLine);
        if (inputLine != null) out.write(inputLine);

        in.close();
        out.close();
        sslSocket.close();
        sslServerSocket.close();
    }
}