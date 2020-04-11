package life.ning.community.controller;

import life.ning.community.dto.AccessTokenDTO;
import life.ning.community.dto.GithubUser;
import life.ning.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;//注意配置文件之间不能有空格、空格行，有空格会报错（网页不知道跳到哪里去）
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("clientId");
        accessTokenDTO.setClient_secret("clientSecret");
        accessTokenDTO.setRedirect_uri("redirectUri");
        accessTokenDTO.setState(state);
        String accessToken = null;
        try {
            accessToken = githubProvider.getAccessToken(accessTokenDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GithubUser user = null;
        try {
            user = githubProvider.getUser(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user.getName());
        return "index";
    }
}
