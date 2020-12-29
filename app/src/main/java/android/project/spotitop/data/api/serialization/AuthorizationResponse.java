package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

public class AuthorizationResponse {
    // Access token
    @SerializedName("access_token")
    String accessToken;

    // Token type
    @SerializedName("token_type")
    String tokenType;

    // Number of seconds the token will expire
    @SerializedName("expires_in")
    int expiresInSec;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresInSec() {
        return expiresInSec;
    }

    public void setExpiresInSec(int expiresInSec) {
        this.expiresInSec = expiresInSec;
    }




}
