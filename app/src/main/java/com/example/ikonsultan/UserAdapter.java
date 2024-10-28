package com.example.ikonsultan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikonsultan.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.PostViewHolder> {
    private List<User> users;
    private final OnPostClickListener listener;

    public interface OnPostClickListener {
        void onPostClick(User user);
    }

    public UserAdapter(List<User> users, OnPostClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        User user = users.get(position);
        holder.id.setText(String.valueOf(user.getId()));
        holder.title.setText(user.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onPostClick(user));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateData(List<User> newUsers) {
        this.users = newUsers;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;

        PostViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            id = itemView.findViewById(R.id.idTxt);
        }
    }
}
