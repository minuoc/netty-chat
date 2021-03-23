package com.chat.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/12
 */
public class Session {
    private String userId;
    private String userName;

    public Session() {
    }

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
