package com.onwl007.apiblog.service;

import com.onwl007.apiblog.config.Constants;
import com.onwl007.apiblog.domain.TokenModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author onwl007@126.com
 * @date 2018/6/10 20:52
 * @desc
 */
@Component
public class TokenManagerService {

    private RedisTemplate redis;

    /**
     * 用户第一次登录，将产生的token存入到redis中
     *
     * @param model
     */
    public void createToken(TokenModel model) {
        //将 token 存储到 redis，并设置过期时间
        redis.boundValueOps(model.getId()).set(model.getToken(), Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    }

    /**
     * 从 header 中获取的token与存入到redis的token之间进行校验
     *
     * @param model
     * @return
     */
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String redisToken = redis.boundValueOps(model.getId()).get().toString();
        if (redisToken == null || !redisToken.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明用户进行了一次有效操作，延长 token 的过期时间
        //redis.boundValueOps(model.getId()).expire(Constants.TOKEN_EXPIRES_HOUR,TimeUnit.HOURS);
        return true;
    }

    public TokenModel getToken(String authentication) {
        if (authentication==null||authentication.length()==0){
            return null;
        }

        return null;
    }

    public void deleteToken(String userId) {

    }
}
