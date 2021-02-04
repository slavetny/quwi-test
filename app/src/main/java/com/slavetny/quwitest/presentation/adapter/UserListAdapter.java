package com.slavetny.quwitest.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.slavetny.quwitest.R;
import com.slavetny.quwitest.domain.model.project.User;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> userList;

    public void attachData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(User user) {
            ImageView userImage = itemView.findViewById(R.id.user_image);
            ImageView onlineStatusIndicator = itemView.findViewById(R.id.online_status_indicator);
            TextView userTitle = itemView.findViewById(R.id.user_name);

            Glide.with(itemView)
                    .load(user.getAvatarUrl())
                    .placeholder(R.drawable.ic_baseline_person_32)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(userImage);

            userTitle.setText(user.getName());

            if (user.isIsOnline()) {
                onlineStatusIndicator.setVisibility(View.VISIBLE);
            } else {
                onlineStatusIndicator.setVisibility(View.GONE);
            }
        }
    }
}