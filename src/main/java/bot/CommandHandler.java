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
		    	chatAPI.sendMessage("Салам!\nВот основые команды:\n", chatID);
			case "/commands":
				for(int i = 0;i<commands.arrayOfCommands.length;i++) 
				{	
					message += commands.arrayOfCommands[i][0] +"\n";
								
				}
				chatAPI.sendMessage(message, chatID);	
				return;
			case "/бан":
				if(replyUser == null) 
				{
					chatAPI.sendMessage("Вы должны ответить на сообщение пользователя, чтобы выполнить данную команду \n", chatID);
					return;
				}
				if(command.length<2) 
				{
					chatAPI.sendMessage("Укажите время бана!", chatID);
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
						int banTime = Integer.parseInt(command[1]);
					chatAPI.banUser(replyUser, chatID, banTime);
					}
					catch(NumberFormatException e) 
					{
						chatAPI.sendMessage("Введите корректное время!", chatID);
					}
				}
				return;
			case "/кик":
				if(replyUser == null) 
				{
					chatAPI.sendMessage("Вы должны ответить на сообщение пользователя, чтобы выполнить данную команду \n", chatID);
					return;
				}
				if(chatAPI.isAdmin(replyUser, chatID)) {
					chatAPI.sendMessage("Вы не можете кикнуть админа", chatID);
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
				
		//sendMessage("ничего не найдено\nнапиши help, чтобы получить список команд", chatID);
	}
}
