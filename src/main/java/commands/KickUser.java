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
			chatAPI.sendMessage("�� ������ �������� �� ��������� ������������, ����� ��������� ������ ������� \n", chatID);
			return;
		}
		if(chatAPI.isAdmin(replyUser, chatID)) {
			chatAPI.sendMessage("�� �� ������ ������� ������", chatID);
			return;
		}
		if(chatAPI.isAdmin(userId, chatID))
			chatAPI.kickUser(replyUser, chatID);
		return;
	}

}
