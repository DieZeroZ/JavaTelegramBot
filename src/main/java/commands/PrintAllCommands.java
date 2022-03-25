package commands;

import bot.ChatAPI;
import bot.CommandsAndAnswers;

public class PrintAllCommands implements CommandInterface {

	CommandsAndAnswers commands;
	String chatID;
	ChatAPI chatAPI;
	public PrintAllCommands(ChatAPI chatAPI,CommandsAndAnswers commands,String chatID) 
	{	
		this.chatAPI = chatAPI;
		this.commands = commands;
		this.chatID = chatID;
	}
	public void makeCommand() 
	{
		String message = null;
		for(String i: commands.arrayOfCommand()) 
		{	
			message += i + "\n";
						
		}
		chatAPI.sendMessage(message, chatID);
	}

}
