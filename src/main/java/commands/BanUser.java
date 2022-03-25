package commands;

import bot.ChatAPI;

public class BanUser implements CommandInterface{
	
	private final ChatAPI chatAPI;
	private final String chatID;
	private final Long userId;
	private final Long replyUser;
	private String command;
	
	public BanUser(ChatAPI chatAPI, String chatID, Long userId, Long replyUser, String command) 
	{
		this.chatID = chatID;
		this.command = command;
		this.chatAPI = chatAPI;
		this.userId = userId;
		this.replyUser = replyUser;
	}
	@Override
	public void makeCommand() {
		
		if(replyUser == null) 
		{
			chatAPI.sendMessage("Вы должны ответить на сообщение пользователя, чтобы выполнить данную команду \n", chatID);
			return;
		}
		if(chatAPI.isAdmin(replyUser, chatID)) 
		{
			chatAPI.sendMessage("Вы не можете забанить админа", chatID);
			return;
		}
		if(chatAPI.isAdmin(userId, chatID)) 
		{
			try {
			//	int banTime = Integer.parseInt(command);
			chatAPI.banUser(replyUser, chatID, 7);
			}
			catch(NumberFormatException e) 
			{
				chatAPI.sendMessage("Введите корректное время!", chatID);
			}
		}
		return;
	}
}
