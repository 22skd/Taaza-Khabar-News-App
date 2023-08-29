package com.example.newsapp;
import com.twilio.converter.Promoter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;

//import java.security.AlgorithmParameterGenerator;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class TwilioHelper {
//    private static final String ACCOUNT_SID = "ACb57bddbd19264019200c5f52a6e0c4e8";
//    private static final String AUTH_TOKEN = "477df32b1162e23b0dd83e4eb1e1eb69";
//    private static final String TWILIO_PHONE_NUMBER = "+13253996479";
//
//    public static void sendMessage(String phoneNumber, String messageBody) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(
//                new PhoneNumber(phoneNumber),
//                new PhoneNumber(TWILIO_PHONE_NUMBER),
//                messageBody
//        ).create();
//
//        System.out.println("Message sent: " + message.getSid());
//    }
//}



public class TwilioHelper {
    private static final String ACCOUNT_SID = "ACb57bddbd19264019200c5f52a6e0c4e8";
    private static final String AUTH_TOKEN = "477df32b1162e23b0dd83e4eb1e1eb69";
    private static final String TWILIO_PHONE_NUMBER = "+13253996479";

    public static void sendMessage(String recipientPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber("whatsapp:" + recipientPhoneNumber),
                new PhoneNumber("whatsapp:" + TWILIO_PHONE_NUMBER),
                message
        ).create();
    }
}



