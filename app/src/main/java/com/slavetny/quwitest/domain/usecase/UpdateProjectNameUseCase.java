package com.slavetny.quwitest.domain.usecase;

import androidx.lifecycle.MutableLiveData;
import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.model.project.Project;
import javax.inject.Inject;

public class UpdateProjectNameUseCase {

    RepositoryImpl repository;

    @Inject
    public UpdateProjectNameUseCase(RepositoryImpl repository) {
        this.repository = repository;
    }

    public MutableLiveData<Project> getProjectList(int projectId, String projectName, String token) {
        return repository.updateProjectName(projectId, projectName, token);
    }
}