package com.example.user.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface mApiInterface=APIClient.getClient().create(ApiInterface.class);
    private TextView text;
    private EditText email,password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitApi();
            }
        });
    }

    public void hitApi() {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("email",email.getText().toString().trim());
        hashMap.put("password",password.getText().toString().trim());
        Call<Object> call = mApiInterface.login(hashMap);
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
