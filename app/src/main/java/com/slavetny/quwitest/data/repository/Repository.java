package com.slavetny.quwitest.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.slavetny.quwitest.domain.model.login.Login;
import com.slavetny.quwitest.domain.model.project.Project;

public interface Repository {
    MutableLiveData<String> getToken(Login login);
    MutableLiveData<Project> getProjectList(String token);
    MutableLiveData<Project> updateProjectName(int projectId, String projectName, String token);
}