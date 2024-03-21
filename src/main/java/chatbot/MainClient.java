package chatbot;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		System.out.println("conectando...");
		ChatbotServerInterface server = (ChatbotServerInterface) Naming.lookup("rmi://127.0.0.1:1098/ChatbotServer");
		System.out.println("conectou...");
		ChatbotClientInterface client = new ChatbotClient();
		client.onConnectionSucess();
	}
}
