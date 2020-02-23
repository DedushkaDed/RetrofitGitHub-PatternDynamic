package chistotest.test.retrofitrabotaet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.List;

import chistotest.test.retrofitrabotaet.R;
import chistotest.test.retrofitrabotaet.api.ServiceGenerator;
import chistotest.test.retrofitrabotaet.api.model.GitHubRepo;
import chistotest.test.retrofitrabotaet.api.service.GitHubClient;
import chistotest.test.retrofitrabotaet.ui.adapter.GitHubRepoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.pagination_list);

        GitHubClient client = ServiceGenerator.createService(GitHubClient.class); // Мой ServiceGenerator
        Call<List<GitHubRepo>> call = client.reposForUser("dedushkaded");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
