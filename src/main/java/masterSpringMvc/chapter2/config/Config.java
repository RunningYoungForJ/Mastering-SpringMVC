package masterSpringMvc.chapter2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

/**
 * Created by yangkun on 2018/3/8.
 */
@Configuration
public class Config {

    @Value("${spring.social.twitter.appId}")
    private String consumerKey;

    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;

    @Bean
    public Twitter twitter(){
        return new TwitterTemplate(consumerKey,consumerSecret);
    }
}
