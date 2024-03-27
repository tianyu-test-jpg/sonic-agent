package org.cloud.sonic.agent.tests.handlers;

/**
 * @Author: Tian Yu
 * @Date: 2024/03/23/21:51
 * @Description:
 */
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FeishuBot {

    public  void sendFeishuMsg(String webhookUrl,
                               String title,
                               String packageName,
                               int executionTime,
                               String osVersion,
                               String deviceSerialNumber,
                               String deviceManufacturer,
                               String deviceModel,
                               String exMsg
    ) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = "{" +
                    "\"msg_type\": \"interactive\"," +
                    "\"card\": {" +
                    "\"config\": {" +
                    "\"wide_screen_mode\": true" +
                    "}," +
                    "\"header\": {" +
                    "\"title\": {" +
                    "\"tag\": \"plain_text\"," +
                    "\"content\": \"" + title + "\"" +
                    "}" +
                    "}," +
                    "\"elements\": [" +
                    "{" +
                    "\"tag\": \"div\"," +
                    "\"text\": {" +
                    "\"tag\": \"lark_md\"," +
                    "\"content\": \"**测试包名:** " + packageName + "\\n" +
                    "**测试时长(min):** " + executionTime + "\\n" +
                    "**测试机操作系统版本:** " + osVersion + "\\n" +
                    "**测试机设备序列号:** " + deviceSerialNumber + "\\n" +
                    "**测试机设备制造商:** " + deviceManufacturer + "\\n" +
                    "**测试机设备型号:** " + deviceModel + "\\n" +
                    "**额外信息:** " + exMsg + "\"" +
                    "}" +
                    "}" +
                    "]" +
                    "}" +
                    "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Failed to send message. Response code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }


    public  void sendFeishuMsg(
            List<String>  webhookUrls,
            String title,
            String packageName,
            int executionTime,
            String osVersion,
            String deviceSerialNumber,
            String deviceManufacturer,
            String deviceModel,
            String exMsg) {

        for (String webhookUrl : webhookUrls) {
            try {
                    URL url = new URL(webhookUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    String jsonPayload = "{" +
                            "\"msg_type\": \"interactive\"," +
                            "\"card\": {" +
                            "\"config\": {" +
                            "\"wide_screen_mode\": true" +
                            "}," +
                            "\"header\": {" +
                            "\"title\": {" +
                            "\"tag\": \"plain_text\"," +
                            "\"content\": \"" + title + "\"" +
                            "}" +
                            "}," +
                            "\"elements\": [" +
                            "{" +
                            "\"tag\": \"div\"," +
                            "\"text\": {" +
                            "\"tag\": \"lark_md\"," +
                            "\"content\": \"**测试包名:** " + packageName + "\\n" +
                            "**测试时长(min):** " + executionTime + "\\n" +
                            "**测试机操作系统版本:** " + osVersion + "\\n" +
                            "**测试机设备序列号:** " + deviceSerialNumber + "\\n" +
                            "**测试机设备制造商:** " + deviceManufacturer + "\\n" +
                            "**测试机设备型号:** " + deviceModel + "\\n" +
                            "**额外信息:** " + exMsg + "\"" +
                            "}" +
                            "}" +
                            "]" +
                            "}" +
                            "}";

                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = jsonPayload.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        System.out.println("Message sent successfully.");
                    } else {
                        System.out.println("Failed to send message. Response code: " + responseCode);
                    }

                    conn.disconnect();
                } catch(Exception e){
                    System.err.println("Exception occurred: " + e.getMessage());
                }

            }
        }
    }




