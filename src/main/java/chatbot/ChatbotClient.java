package chatbot;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatbotClient extends UnicastRemoteObject implements ChatbotClientInterface{

	protected ChatbotClient() throws RemoteException, MalformedURLException, NotBoundException {
		super();	
	}

	@Override
	public void printMessage(String message) throws RemoteException, MalformedURLException, NotBoundException {
		System.out.println(message);
		onConnectionSucess();
	}

	@Override
	public void onConnectionSucess() throws RemoteException, MalformedURLException, NotBoundException {
		Scanner sc = new Scanner(System.in);

		System.out.print("human: ");

		String text = sc.nextLine();
		
		ChatbotServerInterface server = (ChatbotServerInterface) Naming.lookup("rmi://127.0.0.1:1098/ChatbotServer");
	
		server.receiveMessage(text, this);
	}

	@Override
	public void disconnect() {
		System.exit(0);
	}

}
