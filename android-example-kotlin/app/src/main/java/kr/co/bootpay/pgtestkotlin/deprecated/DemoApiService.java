package kr.co.bootpay.pgtestkotlin.deprecated;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.co.bootpay.android.constants.BootpayBuildConfig;
import kr.co.bootpay.android.cookiejar.PersistentCookieJar;
import kr.co.bootpay.android.cookiejar.cache.SetCookieCache;
import kr.co.bootpay.android.cookiejar.persistence.SharedPrefsCookiePersistor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

@Deprecated
public class DemoApiService {
    private Context context;
    private ApiRestApi api;

    public DemoApiService(Context context) {
        this.context = context;

        OkHttpClient client = new OkHttpClient
                .Builder()
//                .addInterceptor(logging)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context)))
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String url = "https://api.bootpay.co.kr/";
        if(BootpayBuildConfig.DEBUG) url = "https://dev-api.bootpay.co.kr/";

        api =  new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiRestApi.class);
    }

    public Context getContext() {
        return this.context;
    }

    public ApiRestApi getApi() { return api; }

    public interface ApiRestApi {


        //이 함수는 서버사이드에서 수행되길 추천합니다. rest_application_id와 private_key는 노출되어서는 안되는 값입니다.
        @Deprecated
        @FormUrlEncoded
        @POST("/v2/request/token")
        Call<TokenData> getRestToken(
                @Field("application_id") String rest_application_id,
                @Field("private_key") String private_key
        );

//        @Deprecated
//        @FormUrlEncoded
//        @POST("/v2/request/token")
//        Observable<TokenData> getRestToken(
//                @Field("application_id") String rest_application_id,
//                @Field("private_key") String private_key
//        );

        //이 함수는 서버사이드에서 수행되길 추천합니다. rest_application_id와 private_key는 노출되어서는 안되는 값입니다.
        @Deprecated
        @FormUrlEncoded
        @POST("/v2/request/user/token")
        Call<EasyPayUserTokenData> getEasyPayUserToken(
                @Header("Authorization") String restToken,
                @Field("user_id") String user_id,
                @Field("email") String email,
                @Field("username") String username,
                @Field("gender") int gender,
                @Field("birth") String birth,
                @Field("phone") String phone
        );

//        @Deprecated
//        @FormUrlEncoded
//        @POST("/v2/request/user/token")
//        Observable<EasyPayUserTokenData> getEasyPayUserToken(
//                @Header("Authorization") String restToken,
//                @Field("user_id") String user_id,
//                @Field("email") String email,
//                @Field("username") String username,
//                @Field("gender") int gender,
//                @Field("birth") String birth,
//                @Field("phone") String phone
//        );


    }
}
