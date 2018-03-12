package masterSpringMvc.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangkun on 2018/3/6.
 */
@Controller
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @RequestMapping("/")
    public String home(){
        return "searchPage";
    }

    @RequestMapping("/home")
    public String helloHome(@RequestParam(value = "username",defaultValue = "RunningYoung")String name,Model model){
        model.addAttribute("message","hello "+name);
        return "homePage";
    }

    @RequestMapping("/twitter")
    public String Twitter(@RequestParam(defaultValue = "masterSpringMVC4")String search, Model model){
        SearchResults searchResults=twitter.searchOperations().search(search);
        String text=searchResults.getTweets().get(0).getText();
        model.addAttribute("tweets",text);
        return "resultPage";
    }

    @RequestMapping("/twitterList")
    public String getTwitterList(@RequestParam(defaultValue = "masterSpringMVC4") String search,Model model){
        SearchResults searchResults=twitter.searchOperations().search(search);
        if (searchResults.getTweets().size()>0){
            List<String> tweets=new ArrayList<>();
            for (Tweet tweet:searchResults.getTweets()){
                tweets.add(tweet.getText());
            }
            model.addAttribute("tweets",tweets);
        }
        return "resultPage";
    }

    @RequestMapping(value = "/allTwitters",method = RequestMethod.GET)
    public String getAllTwitters(@RequestParam(defaultValue = "springMVC") String search,Model model){
        SearchResults searchResults=twitter.searchOperations().search(search);
        List<Tweet> list=searchResults.getTweets();
        model.addAttribute("tweets",list);
        model.addAttribute("search",search);
        return "allTwitters";
    }

    @RequestMapping(value = "/checkSearch",method = RequestMethod.POST)
    public String checkSearch(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String search=request.getParameter("search");
        if (search.toLowerCase().contains("struts")){
            redirectAttributes.addAttribute("error","Try using spring instead!");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("search",search);
        return "redirect:allTwitters";
    }
}