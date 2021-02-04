package com.slavetny.quwitest.presentation.screen.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.slavetny.quwitest.R;
import com.slavetny.quwitest.domain.constants.Constants;
import com.slavetny.quwitest.domain.usecase.GetProjectListUseCase;
import com.slavetny.quwitest.presentation.RecyclerViewMargin;
import com.slavetny.quwitest.presentation.adapter.ProjectListAdapter;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProjectListFragment extends Fragment {

    @Inject
    GetProjectListUseCase getProjectListUseCase;

    private ProjectListAdapter adapter;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        setProjectListObserver();

        adapter.setClickCallback(project -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.PROJECT, project);

            Navigation.findNavController(requireView())
                    .navigate(R.id.action_project_list_to_project_details, bundle);
        });
    }

    private void setProjectListObserver() {
        getProjectListUseCase.getProjectList(getToken()).observe(
                getViewLifecycleOwner(),
                adapter::attachData
        );
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

        adapter = new ProjectListAdapter();
        recyclerView.setAdapter(adapter);
    }

    private String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.TOKEN, Constants.NO_VALUE);
    }
}