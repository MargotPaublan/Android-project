package android.project.spotitop.data.repository.topsongsdisplay.remote;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.util.Date;

public class AuthorizationToken {
    private String tokenBearer;
    private Instant expiresAt;
    private String tokenType;

    private AuthorizationToken()
    {}

    private static AuthorizationToken INSTANCE = new AuthorizationToken();

    public static AuthorizationToken getInstance() {
        return INSTANCE;
    }

    public String getTokenBearer() {
        return tokenBearer;
    }

    public void setTokenBearer(String tokenBearer) {
        this.tokenBearer = tokenBearer;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isExpired() {
        Instant nowInstant = Instant.now().minusSeconds(10);
        return nowInstant.compareTo(expiresAt) > 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setExpiresAt(int seconds) {
        this.expiresAt = Instant.now().plusSeconds(seconds) ;
    }

    public String getTokenAuthorization() {
        return tokenType + " " + tokenBearer;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
