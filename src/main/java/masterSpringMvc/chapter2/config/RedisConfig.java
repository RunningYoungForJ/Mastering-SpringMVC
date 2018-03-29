package masterSpringMvc.chapter2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by yangkun on 2018/3/29.
 */
@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig {
}
