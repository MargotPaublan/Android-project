package android.project.spotitop;

import android.app.Application;
import android.project.spotitop.data.di.FakeDependencyInjection;

import com.facebook.stetho.Stetho;

public class SpotiTopApplication extends Application {
    public static final String API_KEY = "BQD5rGrx9mxMZNXeNck5EfX1GPa94ONn0t-8S1k1gVbtrsdkXsWXA9ijLvqy3ea3mRItJ1mlLRUVnEleaNATIVB2n1qR7oNGlINnnqzMuUhDaMdtUgrA1sQdipLlo6PgebSAk8_-0Wh1SQ";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
