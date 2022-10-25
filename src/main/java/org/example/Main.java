package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            log.info("Запускаємо бота...");
            telegramBotsApi.registerBot(new MessageCalculator());
        } catch (TelegramApiException e){
            log.error("Помилка запуску: перевірте інтернет-з'єднання та коректність токену.\t", e);
        }
        log.info("Телеграм-бот чекає на повідомлення...");
    }
}