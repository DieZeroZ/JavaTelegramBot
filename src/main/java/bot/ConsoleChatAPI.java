package bot;
import java.util.Scanner;

public class ConsoleChatAPI implements ChatAPI 
{
	Scanner in = new Scanner(System.in);
	
	public void sendMessage(String message, String chatID) 
	{
		System.out.printf("%s\n", message);
	}		
	
	public void game() 
	{
		String operators[]= { "+" , "-" , "*" }, input=""; 
		int a,b,typeOfOperation,result=0;
		
		while(true) 
		{
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100)+1;
			typeOfOperation=(int)(Math.random()*3);
			System.out.printf("%d %s %d = ",a,operators[typeOfOperation],b);
			
			switch(typeOfOperation)
			{
				case 0:
					result = a + b;
					break;
				case 1:
					result = a - b;
					break;
				case 2:
					result = a * b;
					break;

			}
			input = in.nextLine().toLowerCase();
			
			if(input.equals("закончить")) return;
			if(Integer.toString(result).equals(input)) 
			{
				sendMessage("Верно!", "Console");
			}else 
			{
				sendMessage("Мимо","Console");
			}
		}
	}
	public String getCommand() 
	{	 
			 return(in.nextLine().toLowerCase());	
	}

	@Override
	public void banUser(Long userId, String chatId, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kickUser(Long userId, String chatId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMemberId(String userName, String chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAdmin(Long userId, String chatId) {
		// TODO Auto-generated method stub
		return null;
	}

}
