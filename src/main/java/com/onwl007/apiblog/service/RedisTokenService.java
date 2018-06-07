package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.TokenModel;
import com.onwl007.apiblog.repository.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author onwl007@126.com
 * @date 2018/6/7 20:13
 * @desc 通过 Redis 存储和验证 token 的实现类
 */
@Component
public class RedisTokenService implements TokenManager {

    private RedisTemplate<Long, String> redis;


    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成 Long 后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(String userId) {
        //使用 uuid 作为源 token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        //存储到 redis 并设置过期时间
        redis.boundValueOps(Long.parseLong(userId)).set(token, 72, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        long userId = Long.parseLong(model.getId());
        String token = redis.boundValueOps(userId).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
        redis.boundValueOps(userId).expire(72, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用 userId 和源 token 简单拼接成的 token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(String userId) {
        redis.delete(Long.parseLong(userId));
    }
}
