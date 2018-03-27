package masterSpringMvc.chapter4.search;

import masterSpringMvc.chapter5.entity.LightTweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.Oneway;
import java.util.List;

/**
 * Created by yangkun on 2018/3/20.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchSerivce;

    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords){
        List<LightTweet> tweets=searchSerivce.search(searchType,keywords);
        ModelAndView modelAndView=new ModelAndView("allTwitters");
        modelAndView.addObject("tweets",tweets);
        modelAndView.addObject("search",String.join(",",keywords));
        return modelAndView;
    }

}
