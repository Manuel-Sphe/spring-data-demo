package sphe.dev.restdemo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InseeAuthApiResponse {
    @JsonProperty("access_token")
    private String accessToken;

    /**
     *
     * @return the accessToken
     */

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
