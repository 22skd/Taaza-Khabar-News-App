package com.example.newsapp;
import com.twilio.converter.Promoter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;





public class TwilioHelper {
    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";
    private static final String TWILIO_PHONE_NUMBER = "";

    public static void sendMessage(String recipientPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber("whatsapp:" + recipientPhoneNumber),
                new PhoneNumber("whatsapp:" + TWILIO_PHONE_NUMBER),
                message
        ).create();
    }
}



