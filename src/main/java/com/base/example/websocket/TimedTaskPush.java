package com.base.example.websocket;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

/**
 * @description: --定时推送日期信息
 * @author：Bing
 * @date：2022/6/8 13:37
 * @version：1.0
 */
@Component
@Slf4j
public class TimedTaskPush {

    /**
     * 向所有在线用户推送日期
     * 定时推送日期
     * 1s执行1次
     */
    @Scheduled(cron = "0/1 * * * * ? ")
    public void pushMessage() {
        DateTime date = DateUtil.date();
        String format = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
        Map<String, Session> sessionMap = WebSocketServer.sessionMap;
        if (sessionMap.size() != 0) {
            for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
                //String key = entry.getKey();
                Session session = entry.getValue();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.set("time", "all");  // from 是 zhang
                    jsonObject.set("text", format);  // text 同上面的text
                    session.getBasicRemote().sendText(jsonObject.toString());
                    log.info("定时推送消息 {}", format);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}