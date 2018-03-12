package masterSpringMvc.chapter2.config;

import masterSpringMvc.chapter3.utils.USLocalDateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;

/**
 * Created by yangkun on 2018/3/8.
 */
@Configuration
public class Config extends WebMvcConfigurerAdapter{

    /*@Value("${spring.social.twitter.appId}")
    private String consumerKey;

    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;

    @Bean
    public Twitter twitter(){
        return new TwitterTemplate(consumerKey,consumerSecret);
    }*/

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class,new USLocalDateFormatter());
    }
}
