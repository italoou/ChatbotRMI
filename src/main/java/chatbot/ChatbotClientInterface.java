package chatbot;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatbotClientInterface extends Remote {

	public void printMessage(String message) throws RemoteException, MalformedURLException, NotBoundException;

	public void onConnectionSucess() throws RemoteException, MalformedURLException, NotBoundException;
	
	public void disconnect() throws RemoteException;
}
