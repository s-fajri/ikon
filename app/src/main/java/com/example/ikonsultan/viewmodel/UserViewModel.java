package com.example.ikonsultan.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ikonsultan.APIRepository;
import com.example.ikonsultan.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final APIRepository apiRepository;
    private final MutableLiveData<List<User>> user = new MutableLiveData<>();


    public UserViewModel(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
        fetchUser();
    }

    private void fetchUser() {
        apiRepository.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    user.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<User>> getUsers() {
        return user;
    }
}
