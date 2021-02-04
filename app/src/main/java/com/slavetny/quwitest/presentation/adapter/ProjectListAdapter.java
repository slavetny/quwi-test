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
import com.slavetny.quwitest.domain.model.project.Project;
import com.slavetny.quwitest.domain.model.project.ProjectResponse;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private Project project;
    private static ClickCallback clickCallback;

    public void attachData(Project project) {
        this.project = project;
        notifyDataSetChanged();
    }

    public void setClickCallback(ClickCallback clickCallback) {
        ProjectListAdapter.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(project.getProjects().get(position));
    }

    @Override
    public int getItemCount() {
        return project.getProjects().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(ProjectResponse project) {
            ImageView projectImage = itemView.findViewById(R.id.project_image);
            TextView projectTitle = itemView.findViewById(R.id.project_title);

            Glide.with(itemView)
                    .load(project.getLogoUrl())
                    .placeholder(R.drawable.ic_baseline_image_32)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(projectImage);

            projectTitle.setText(project.getName());

            itemView.setOnClickListener(v ->
                    clickCallback.clickCallback(project));
        }
    }

    public interface ClickCallback {
        void clickCallback(ProjectResponse project);
    }
}