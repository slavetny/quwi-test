package com.slavetny.quwitest.presentation.screen.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.slavetny.quwitest.R;
import com.slavetny.quwitest.domain.constants.Constants;
import com.slavetny.quwitest.domain.model.project.ProjectResponse;
import com.slavetny.quwitest.domain.usecase.UpdateProjectNameUseCase;
import com.slavetny.quwitest.presentation.RecyclerViewMargin;
import com.slavetny.quwitest.presentation.adapter.UserListAdapter;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProjectDetailsFragment extends Fragment {

    @Inject
    UpdateProjectNameUseCase updateProjectNameUseCase;

    private UserListAdapter adapter;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_details, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();

        if(getArguments() != null) {
            ProjectResponse project = (ProjectResponse) getArguments().get(Constants.PROJECT);

            ImageView projectImage = requireView().findViewById(R.id.project_image);
            TextView projectTitle = requireView().findViewById(R.id.project_title);

            Glide.with(requireView())
                    .load(project.getLogoUrl())
                    .placeholder(R.drawable.ic_baseline_image_32)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(projectImage);

            projectTitle.setText(project.getName());

            adapter.attachData(project.getUsers());

            projectTitle.setOnClickListener(v ->
                    requestNewNameDialog(project));
        }
    }

    private void requestNewNameDialog(ProjectResponse project) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Title");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("Ok", (dialog, which) ->
                updateProjectName(project, input.getText().toString()));

        builder.setNegativeButton("Cancel", (dialog, which) ->
                dialog.cancel());

        builder.show();
    }

    private void updateProjectName(ProjectResponse projectResponse, String name) {
        updateProjectNameUseCase.getProjectList(
                projectResponse.getId(),
                name,
                getToken()
        ).observe(getViewLifecycleOwner(), project ->
                Navigation.findNavController(requireView()).popBackStack());
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewMargin decoration = new RecyclerViewMargin((int) getResources().getDimension(R.dimen.default_margin), 1);
        recyclerView.addItemDecoration(decoration);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new UserListAdapter();
        recyclerView.setAdapter(adapter);
    }

    private String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.TOKEN, Constants.NO_VALUE);
    }
}