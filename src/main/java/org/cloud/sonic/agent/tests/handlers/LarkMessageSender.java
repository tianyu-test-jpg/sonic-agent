package org.cloud.sonic.agent.tests.handlers;

/**
 * @Author: Tian Yu
 * @Date: 2024/03/20/21:02
 * @Description:
 */
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LarkMessageSender {

    // 发送消息的方法
    public void sendMessage(String message) {
        String robotToken = "302488a2-02f2-43d5-8a95-bd06304d8f04";
        String robotSecret = "";

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://open.feishu.cn/open-apis/bot/v2/hook/" + robotToken;

        // 构建消息内容，此处以文本消息为例
        String textMessage = "{\"msg_type\":\"text\",\"content\":{\"text\":\"" + message + "\"}}";

        // 设置HTTP请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + robotSecret);

        // 发送POST请求
        HttpEntity<String> entity = new HttpEntity<>(textMessage, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // 打印响应结果
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }

    public static void main(String[] args) {
        LarkMessageSender sender = new LarkMessageSender();
        sender.sendMessage("Hello, World!");
    }
}
