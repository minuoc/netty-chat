package com.chat.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/12
 */

@AllArgsConstructor
@Data
public class Session {
    private String userId;
    private String userName;
}
