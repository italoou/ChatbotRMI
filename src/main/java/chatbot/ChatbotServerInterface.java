package chatbot;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatbotServerInterface extends Remote {

	public void receiveMessage(String text, ChatbotClientInterface chatbotClient) throws RemoteException, MalformedURLException, NotBoundException;
	
}
