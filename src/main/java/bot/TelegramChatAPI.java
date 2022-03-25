package bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import commands.CommandInterface;

import java.util.ArrayList;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.*;


public class TelegramChatAPI extends TelegramLongPollingBot implements ChatAPI 
{	
	private String botToken = System.getenv("TOKEN");
	private CommandHandler handler = new CommandHandler(this);
	private CommandInterface command;
	
	public void sendMessage(String message, String chatID)
	{
		SendMessage messageToUser = new SendMessage();
		messageToUser.setText(message);
		messageToUser.setChatId(chatID);
		try {
			execute(messageToUser);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void onUpdateReceived(Update update) 
	{
		// List<MessageEntity> messsage = update.getMessage().getEntities();
		ChatMemberMember member = new ChatMemberMember();
		member.setUser(update.getMessage().getFrom());
		System.out.printf("%s", member.getStatus());
		
		if (update.hasMessage() && update.getMessage().hasText()) {
	      //  messageFromUser =update.getMessage().getText().toLowerCase();
	       // userChatID = update.getMessage().getChatId().toString();
			System.out.printf("%s    %s\n",update.getMessage().getText().toLowerCase(),update.getMessage().getFrom().getUserName().toString());
			 
			command = handler.handler(update.getMessage().getText().toLowerCase(), update.getMessage().getChatId().toString(),update.getMessage().getFrom().getId(),getIdOfReplyUser(update.getMessage()));
			if(command != null)
			command.makeCommand();
	        System.out.println(update.getMessage().getEntities());
	     // sendMessage(update.getMessage().getText(),update.getMessage().getChatId().toString()); // Call method to send the message
	    }
		
	}
	
	private Long getIdOfReplyUser(Message replyedMessage) 
	{
		if(replyedMessage.getReplyToMessage()!=null) 
		{
			return replyedMessage.getReplyToMessage().getFrom().getId();
		}
		return null;
	}

	public String getBotUsername() {
		return "pivovar";
	}

	public String getBotToken() {
		 return botToken;
	}
	
	@Override
	public void banUser(Long userId, String chatId, int time) {
		BanChatMember ban = new BanChatMember();
		ban.setUserId(userId);
		ban.setChatId(chatId);
		ban.setUntilDate(time);
		
		try {
			execute(ban);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void kickUser(Long userId, String chatId) {
		KickChatMember kick = new KickChatMember();
		kick.setChatId(chatId);
		kick.setUserId(userId);
		
		try {
			execute(kick);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String getMemberId(String userName, String chatId) {
		
		return null;
	}

	@Override
	public Boolean isAdmin(Long userId, String chatId) {
		GetChatAdministrators admins = new GetChatAdministrators(chatId);
		ArrayList<ChatMember> chatAdminsList = new ArrayList<ChatMember>();
		
		try {
			chatAdminsList = execute(admins);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
		ChatMember member = null;
		GetChatMember checkableUser = new GetChatMember(chatId,userId);
		
		try {
			member = execute(checkableUser);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
		for(ChatMember memb : chatAdminsList) 
		{
			if(memb.getStatus().equals(member.getStatus())) 
			{
				System.out.println("...");
				return true;
			}
		}
		
		
		return false;
	}
}
