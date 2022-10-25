package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageCalculator extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private int messageCount = 0;
    private String botsAnswer;

    @Override
    public String getBotUsername() {
        return "Bot";
    }

    @Override
    public String getBotToken() {
        return "***";
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();
            Long userId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);
            messageCount++;
            botsAnswer = (textFromUser == "Стоп") ?
                    "Всього повідомлень:\t" + messageCount :
                    "Повідомлення №" + messageCount + " від " + userFirstName;

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(userId.toString())
                    .text(botsAnswer)
                    .build();
            try {
                this.sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Помилка при відправленні повідомлення:\t", e);
            }
        } else {
            log.warn("Неочікувана дія від користувача");
        }
    }
}
