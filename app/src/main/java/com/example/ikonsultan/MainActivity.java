package com.example.ikonsultan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikonsultan.model.User;
import com.example.ikonsultan.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText searchInput = findViewById(R.id.searchInput);
        ImageButton searchButton = findViewById(R.id.searchButton);

        userAdapter = new UserAdapter(userList, user -> {
            Intent intent = new Intent(MainActivity.this, DetailUser.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }
        });

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            if (!TextUtils.isEmpty(query)) {
                List<User> filteredList = new ArrayList<>();
                for (User user : userList) {
                    if (user.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        filteredList.add(user);
                    }
                }
                userAdapter.updateData(filteredList);
            } else {
                userAdapter.updateData(userList);
            }
        });
    }
}
