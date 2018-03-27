package masterSpringMvc.chapter5.entity;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by yangkun on 2018/3/27.
 */
public class LightTweet {
    private String profileImageUrl;
    private String user;
    private String text;
    private String lang;
    private LocalDateTime date;
    private Integer retweetCount;

    public LightTweet(String text){
        this.text=text;
    }

    public static LightTweet ofTweet(Tweet tweet){
        LightTweet lightTweet=new LightTweet(tweet.getText());
        Date createedAt =tweet.getCreatedAt();
        if (createedAt!=null){
            lightTweet.date=LocalDateTime.ofInstant(createedAt.toInstant(), ZoneId.systemDefault());
        }
        TwitterProfile profile=tweet.getUser();
        if (profile!=null){
            lightTweet.user=profile.getName();
            lightTweet.profileImageUrl=profile.getProfileImageUrl();
        }
        lightTweet.lang=tweet.getLanguageCode();
        lightTweet.retweetCount=tweet.getRetweetCount();
        return lightTweet;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getLang() {
        return lang;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }
}
