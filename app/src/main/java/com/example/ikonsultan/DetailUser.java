package com.example.ikonsultan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ikonsultan.model.User;

public class DetailUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        User user = (User) getIntent().getSerializableExtra("user");

        TextView userIdTxt = findViewById(R.id.userId);
        TextView idTxt = findViewById(R.id.id);
        TextView titleTxt = findViewById(R.id.title);
        TextView bodyTxt = findViewById(R.id.body);

        if (user != null) {
            userIdTxt.setText(String.valueOf(user.getUserId()));
            idTxt.setText(String.valueOf(user.getId()));
            titleTxt.setText(user.getTitle());
            bodyTxt.setText(user.getBody());
        }
    }
}
