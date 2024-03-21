package chatbot;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServer {

	public static void main(String[] argsW) throws RemoteException, MalformedURLException {
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		
		ChatbotServerInterface server = new ChatbotServer();
		
		LocateRegistry.createRegistry(1098);
		
		Naming.rebind("rmi://127.0.0.1:1098/ChatbotServer", server);
		
		System.out.println(LocateRegistry.getRegistry("127.0.0.1", 1098).toString());
		
		System.out.println("Chatbot Server Started");
	}
}
