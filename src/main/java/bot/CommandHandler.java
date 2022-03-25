package bot;

import commands.BanUser;
import commands.CommandInterface;
import commands.KickUser;
import commands.PrintAllCommands;
import commands.SimpleAnswer;

public class CommandHandler 
{	private final ChatAPI chatAPI;
	private CommandsAndAnswers commands = new  CommandsAndAnswers();
	
	public CommandHandler(ChatAPI chat) 
	{
		this.chatAPI = chat;
	}
	
	public CommandInterface handler(String command, String chatID, Long userId, Long replyUser) 
	{
		int randomCommand;
		String message = "";
		switch(command)
		{
			case "/commands":
				return new PrintAllCommands(chatAPI,commands,chatID);
			case "/���":
				return new BanUser(chatAPI,chatID,userId,replyUser,command);
			case "/���":
				return new KickUser(chatAPI,chatID,userId,replyUser);
			default:
				
				if(commands.GetAnswer(command)!= null) {
					return new SimpleAnswer(chatAPI,commands.GetAnswer(command),chatID);
				}
		}
		return null;
				
		//sendMessage("������ �� �������\n������ help, ����� �������� ������ ������", chatID);
	}
}
