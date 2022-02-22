package bot;


public class CommandHandler 
{	private final ChatAPI chatAPI;
	private CommandsAndAsnwers commands = new  CommandsAndAsnwers();
	
	public CommandHandler(ChatAPI chat) 
	{
		this.chatAPI = chat;
	}
	
	public void handler(String[] command, String chatID, Long userId, Long replyUser) 
	{
		int randomCommand;
		String message = "";
		switch(command[0])
		{
		    case "/start":
		    	chatAPI.sendMessage("�����!\n��� ������� �������:\n", chatID);
			case "/commands":
				for(int i = 0;i<commands.arrayOfCommands.length;i++) 
				{	
					message += commands.arrayOfCommands[i][0] +"\n";
								
				}
				chatAPI.sendMessage(message, chatID);	
				return;
			case "/���":
				if(replyUser == null) 
				{
					chatAPI.sendMessage("�� ������ �������� �� ��������� ������������, ����� ��������� ������ ������� \n", chatID);
					return;
				}
				if(command.length<2) 
				{
					chatAPI.sendMessage("������� ����� ����!", chatID);
					return;
				}
				if(chatAPI.isAdmin(replyUser, chatID)) 
				{
					chatAPI.sendMessage("�� �� ������ �������� ������", chatID);
					return;
				}
				if(chatAPI.isAdmin(userId, chatID)) 
				{
					try {
						int banTime = Integer.parseInt(command[1]);
					chatAPI.banUser(replyUser, chatID, banTime);
					}
					catch(NumberFormatException e) 
					{
						chatAPI.sendMessage("������� ���������� �����!", chatID);
					}
				}
				return;
			case "/���":
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
		
		for(int i =0;i<commands.arrayOfCommands.length;i++) 
		{
			if(commands.arrayOfCommands[i][0].toLowerCase().equals(command)) 
			{	
				randomCommand = (int)(Math.random()*(commands.arrayOfCommands[i].length-1))+1;
				chatAPI.sendMessage(commands.arrayOfCommands[i][randomCommand], chatID);
				return;
			}
		}
				
		//sendMessage("������ �� �������\n������ help, ����� �������� ������ ������", chatID);
	}
}
