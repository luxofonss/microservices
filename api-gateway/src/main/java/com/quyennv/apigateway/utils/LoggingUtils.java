package com.quyennv.apigateway.utils;

import com.quyennv.apigateway.constant.HeaderEnum;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class LoggingUtils {
    public static List<String> dataSecure = Arrays.asList(HeaderEnum.X_API_KEY.getKey(), HeaderEnum.X_API_SECRET.getKey(), "Authorization");

    private static final String REGEX_FILTER_KEY = "[\\\\]*\\\"%s[\\\\]*\\\"[:]+((?=\\[)\\[[^]]*\\]|(?=\\{)\\{[^\\}]*\\}|\\\"[^\"]*\\\"|(\\d+(\\.\\d+)?)|([\\\\]*\\\"[^\"]*\\\"))";


    public static String redactSensitiveData(String logMessage) {
        for (String key : dataSecure) {
            String pat = String.format(REGEX_FILTER_KEY, key);

            Matcher matcher = java.util.regex.Pattern.compile(pat).matcher(logMessage);
            while (matcher.find()) {
                String group = matcher.group();
                String[] split = group.split(":");
                String value = split[1].trim();
                logMessage = logMessage.replace(value, "\"***\"");
            }
        }
        return logMessage;
    }
}
