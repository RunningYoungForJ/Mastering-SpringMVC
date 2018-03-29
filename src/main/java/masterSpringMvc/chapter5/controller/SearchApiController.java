package masterSpringMvc.chapter5.controller;

import masterSpringMvc.chapter4.search.SearchService;
import masterSpringMvc.chapter5.entity.LightTweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yangkun on 2018/3/27.
 */
@RestController
@RequestMapping(value = "/api/search")
public class SearchApiController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List<LightTweet> search(@PathVariable(value = "searchType") String searchTyep,
            @MatrixVariable List<String> keywords) {
        return searchService.search(searchTyep, keywords);

    }
}
