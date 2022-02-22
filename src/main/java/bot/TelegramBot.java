package bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot  {
	
	private TelegramChatAPI chatAPI = new TelegramChatAPI();
    public void startBot()
    {
            TelegramBotsApi botsApi = null;
			try {
				botsApi = new TelegramBotsApi(DefaultBotSession.class);
			} catch (TelegramApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				botsApi.registerBot(chatAPI);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();//402363825
			}
    }		
}