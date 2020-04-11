package life.ning.community.provider;

import com.alibaba.fastjson.JSON;
import life.ning.community.dto.AccessTokenDTO;
import life.ning.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component

public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                //String[] split = response.body().string().split("&");
               // String tokenstr = split[0];
                String token = response.body().string().split("&")[0].split("=")[1];
                return token;

            }
        }
        public GithubUser getUser(String accessToken) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                        .url("https://api.github.com/user?access_token="+ accessToken)
                        .build();
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        }
    }


