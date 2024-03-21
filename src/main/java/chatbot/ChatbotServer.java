package chatbot;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;

public class ChatbotServer extends UnicastRemoteObject implements ChatbotServerInterface {

	private static final boolean TRACE_MODE = false;

	
	protected ChatbotServer() throws RemoteException {
		super();
	}

	@Override
	public void receiveMessage(String text, ChatbotClientInterface client) throws RemoteException, MalformedURLException, NotBoundException {
		System.out.println("Human: " + text);
		
		String resourcesPath = getResourcesPath();
		MagicBooleans.trace_mode = TRACE_MODE;
		Bot bot = new Bot("super", resourcesPath);
		Chat chatSession = new Chat(bot);
		bot.brain.nodeStats();


		if ((text == null) || (text.length() < 1))
			text = MagicStrings.null_input;
		if (text.equals("q")) {
			client.disconnect();
		} else if (text.equals("wq")) {
			bot.writeQuit();
			client.disconnect();
		} else {
			String request = text;
			if (MagicBooleans.trace_mode)
				System.out.println(
						"STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
								+ ":TOPIC=" + chatSession.predicates.get("topic"));
			String response = chatSession.multisentenceRespond(request);
			while (response.contains("&lt;"))
				response = response.replace("&lt;", "<");
			while (response.contains("&gt;"))
				response = response.replace("&gt;", ">");
			System.out.println("Bot: " + response);
			client.printMessage("Bot: "+response);
		}
	}
	
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 16);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
}
