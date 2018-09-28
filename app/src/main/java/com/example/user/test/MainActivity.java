package com.example.user.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface mApiInterface=APIClient.getClient().create(ApiInterface.class);
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        hitApi();
    }

    public void hitApi() {
        Call<Object> call = mApiInterface.getData();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                try {
                    text.setText(new Gson().toJson(response.body()) );
                    Log.e("TAG", "Data get>>>\n: "+new Gson().toJson(response.body()) );
                } catch (Exception exception) {
                    Log.e("TAG", "Exception>>>\n: "+exception );
                    text.setText("Exception\n"+exception.toString());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                call.cancel();
                Log.e("TAG", "Failure>>>\n: "+t );
                text.setText("onFailure\n"+t.toString());
            }
        });
    }
}
