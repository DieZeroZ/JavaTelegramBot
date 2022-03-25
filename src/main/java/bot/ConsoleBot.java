package bot;

public class ConsoleBot 
{	
	CommandHandler commandHandler = new CommandHandler(new ConsoleChatAPI());
	ConsoleChatAPI chatAPI = new ConsoleChatAPI();
	
	public void botStart() 
	{		
		chatAPI.sendMessage("Привет! Я - бот Пивовар v1.0\n", "Console");
		while(true) 
		{
			 chatAPI.sendMessage("Ведите команду: ", "Console");
			 commandHandler.handler(chatAPI.getCommand(), "Console",0l,0l);
			// commandHandler.handler(in.nextLine().toLowerCase(),"Console");
		}
	}
}
