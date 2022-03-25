package bot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandsAndAnswers 
{
	private List<ArrayList<String>> commandsArray = new ArrayList<>();
	
	public CommandsAndAnswers() 
	{
		for(String[] str :allCommands) 
		{
			this.addCommand(str[0], str[1]);
		}
	}
	
	public void addCommand(String command, String answer) 
	{
		ArrayList<String> command1 = new ArrayList<>();
		command1.add(command);
		command1.add(answer);
		commandsArray.add(command1);
	}
	
	public String GetAnswer(String command) 
	{
		for(ArrayList<String> commandd :commandsArray) 
		{
			if(commandd.contains(command)) 
			{
				return commandd.get(1);
			}
		}
		return null;
	}

	public ArrayList<String> arrayOfCommand() 
	{
		ArrayList<String> ret = new ArrayList<String>();
		for(ArrayList<String> commandd :commandsArray) 
		{
			ret.add(commandd.get(0));
		}
		System.out.print(ret);
		return ret;
	}
	
	private String[][] allCommands = new String[][]  
			{
			{"/расскажи что-нибудь","Клетки твоего организма работают не покладая рук, чтобы ты жил. А ты не можешь дописать бота на java"}, 
			{"/анекдот","Русалка села на шпагат"},
			{"/commands", null},
			{"/вопрос", "Ответ"},
			{"/бан",null},
			{"/кик", null}};
}
