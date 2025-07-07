package uz.qodirov.unittest;

import java.util.Random;

public class DynamicPatternGenerator {
    private static final Random random = new Random();
    private static final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";

    static String[] patterns = {
            "^\\{\"s_id\":343,\"p_acc\":\"\\d+\"}$",
            "^https:\\/\\/my\\.click\\.uz\\/services\\/pay\\?service_id=\\d+&merchant_id=(\\d+)&amount=([\\d,]+)&transaction_param=(\\d+)$",
            "^\\{\"qrv\":\"1\\.0\",\"details\":\\{\"amount\":\\d+,\"phone\":\"[^\\\"]+\"},\"payme\":\"[a-z0-9]+\"}$",
            "^\\{\"qrv\":\"1\\.0\",\"details\":\\{\"amount\":\\d+,\"region\":\"[^\\\"]+\",\"subregion\":\"[^\\\"]+\",\"account\":\"[^\\\"]+\"},\"payme\":\"elektr\"}$",
            "^D0(?=.*D1)(?=.*D2)(?=.*D3)(?=.*D4)(?=.*D5)(?=.*D6)(?=.*D7).*",
            "^\\{\"qrv\":\"1\\.0\",\"details\":\\{\"amount\":\\d+,\"invoice\":\"[^\\\"]+\"},\"payme\":\"hunarmand\"}$",
            "^\\{\"merchantId\":\"mrt_2xUlMPmoEcHKPRTtfoR1M8CKRKg\",\"amount\":\\d+,\"accounts\":\\{\"account\":\"[^\\\"]+\"}}$"
    };

    public static void main(String[] args) {

        for (String pattern : patterns) {
            System.out.println("pattern = " + pattern);
            String generate = replaceDigitPatterns(pattern);
            System.out.println("generate = " + generate);
        }
    }

    public static String replaceDigitPatterns(String input) {
        if (input.contains("\\\\d\\+")) {
            input = input.replaceAll("\\\\d\\+", String.valueOf(random.nextInt(999999) + 1));
        }
        if (input.contains("([\\d,]+)")) {
            String replacement = generateDigitsWithCommas();
            input = input.replaceAll("([\\\\d,]+)", replacement);
        }
        if (input.equals("[\\d,]+")) {
            String replacement = generateDigitsWithCommas();
            input = input.replaceAll("[\\\\d,]+", replacement);
        }
        if (input.contains("[a-z0-9]+")) {
            input = input.replaceAll("[a-z0-9]+", generateAlphaNum());
        }
        if (input.equals("[^\\\"]+")) {
            input = input.replaceAll("[^\\\"]+", generateAlphaNum());
        }
        return input;
    }

    public static String generateDigitsWithCommas() {
        int partsCount = random.nextInt(5) + 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < partsCount; i++) {
            int digitsCount = random.nextInt(4) + 1;
            for (int j = 0; j < digitsCount; j++) {
                sb.append(random.nextInt(10));
            }
            if (i < partsCount - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

    public static String generateAlphaNum() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            sb.append(CHAR_POOL.charAt(index));
        }
        return sb.toString();
    }
}