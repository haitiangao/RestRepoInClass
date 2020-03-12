package com.example.restrepoinclass.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.restrepoinclass.R;
import com.example.restrepoinclass.model.GitResult;
import com.example.restrepoinclass.network.GitRetrofitInstance;
import com.example.restrepoinclass.util.DebugLogger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //private GitUserFragment exampleFragment = new GitUserFragment();
    //private RepositoryFragment secondFragment = new RepositoryFragment();

    private GitRetrofitInstance retrofitInstance = new GitRetrofitInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRepositories();
    }

    private void getRepositories() {
        retrofitInstance.getRepos("jdeshaies1982")
                .enqueue(new Callback<List<GitResult>>() {
                    @Override
                    public void onResponse(Call<List<GitResult>> call, Response<List<GitResult>> response) {

                        for (int i = 0; i < response.body().size(); i++) {
                            DebugLogger.logDebug(response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GitResult>> call, Throwable t) {
                        DebugLogger.logError(new Exception(t));
                    }
                });
    }

/*
    public void onOpenFragments(View view) {

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame, exampleFragment)
                .addToBackStack(exampleFragment.getTag())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame_bottom, secondFragment)
                .addToBackStack(secondFragment.getTag())
                .commit();
    }
*/

    //public void communicateToSecond(String message) {
    //    secondFragment.displayMessage(message);
    //}
}
