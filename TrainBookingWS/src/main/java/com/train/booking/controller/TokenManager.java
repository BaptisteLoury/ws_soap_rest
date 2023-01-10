package com.train.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddkkmmss");
    
    public String genToken() {
        String token = RandomStringUtils.randomAlphanumeric(10);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 20);

        String expiration = format.format(calendar.getTime());

        return token + "-" + expiration;
    }

    public boolean isTokenExpired(String token) {
        boolean tokenExpired = true;

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{10}(?<date>[0-9]{14})");
        Matcher matcher = pattern.matcher(token);

        if(matcher.matches()) {
            try {
                Date tokenDate = format.parse(matcher.group("date"));
                tokenExpired = new Date(System.currentTimeMillis()).compareTo(tokenDate) > 0;
            } catch (ParseException e) {
                // do nothing
            }
        }

        return tokenExpired;
    }
}
