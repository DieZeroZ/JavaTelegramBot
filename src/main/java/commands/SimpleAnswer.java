package commands;

import bot.ChatAPI;

public class SimpleAnswer implements CommandInterface {

	private ChatAPI chatAPI;
	private String answer;
	private String chatID;
	
	public  SimpleAnswer(ChatAPI chatAPI, String answer, String chatID) 
	{
		this.chatAPI = chatAPI;
		this.chatID = chatID;
		this.answer = answer;
	}
	
	public void makeCommand() {
		chatAPI.sendMessage(answer, chatID);
	}

}
