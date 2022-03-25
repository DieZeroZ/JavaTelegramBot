package commands;

import bot.ChatAPI;

public class KickUser implements CommandInterface {

	private final ChatAPI chatAPI;
	private final String chatID;
	private final Long userId;
	private final Long replyUser;
	
	public KickUser(ChatAPI chatAPI, String chatID, Long userId, Long replyUser) 
	{
		this.chatID = chatID;
		this.chatAPI = chatAPI;
		this.userId = userId;
		this.replyUser = replyUser;
	}
	@Override
	public void makeCommand() {
		
		if(replyUser == null) 
		{
			chatAPI.sendMessage("¬ы должны ответить на сообщение пользовател€, чтобы выполнить данную команду \n", chatID);
			return;
		}
		if(chatAPI.isAdmin(replyUser, chatID)) {
			chatAPI.sendMessage("¬ы не можете кикнуть админа", chatID);
			return;
		}
		if(chatAPI.isAdmin(userId, chatID))
			chatAPI.kickUser(replyUser, chatID);
		return;
	}

}
