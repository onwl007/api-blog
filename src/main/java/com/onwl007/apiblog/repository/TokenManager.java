package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.TokenModel;

/**
 * @author onwl007@126.com
 * @date 2018/6/6 21:32
 * @desc TokenModel 持久层
 */
public interface TokenManager {

    /**
     * 创建一个 token 关联指定用户
     *
     * @param userId   指定用户的 ID
     * @param username 指定用户的 username
     * @return
     */
    public TokenModel createToken(String userId, String username);

    /**
     * 检查 token 是否有效
     *
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析 token
     *
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     * 清除 token
     *
     * @param userId 登陆用户的 id
     */
    public void deleteToken(String userId);
}
