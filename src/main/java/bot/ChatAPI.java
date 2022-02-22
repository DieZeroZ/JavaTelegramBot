package bot;


public interface ChatAPI {
	void sendMessage(String message, String chatID);	
	public void banUser(Long userId,String chatId,int time);
	public void kickUser(Long userId,String chatId);
	public String getMemberId(String userName,String chatId);
	public Boolean isAdmin(Long userId,String chatId);
}
