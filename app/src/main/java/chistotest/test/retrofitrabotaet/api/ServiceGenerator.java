package chistotest.test.retrofitrabotaet.api;

import chistotest.test.retrofitrabotaet.api.service.GitHubClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

//        Gson gson = new GsonBuilder().serializeNulls().setDateFormat(DateFormat.LONG).create();

    private static final String BASE_URL = "https://api.github.com/";

    private static  Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
