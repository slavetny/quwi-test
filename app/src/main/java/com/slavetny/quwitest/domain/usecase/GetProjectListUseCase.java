package com.slavetny.quwitest.domain.usecase;

import androidx.lifecycle.MutableLiveData;
import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.model.project.Project;

import javax.inject.Inject;

public class GetProjectListUseCase {

    RepositoryImpl repository;

    @Inject
    public GetProjectListUseCase(RepositoryImpl repository) {
        this.repository = repository;
    }

    public MutableLiveData<Project> getProjectList(String token) {
        return repository.getProjectList(token);
    }
}