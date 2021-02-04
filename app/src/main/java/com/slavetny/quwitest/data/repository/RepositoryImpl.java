package com.slavetny.quwitest.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.slavetny.quwitest.data.network.QuwiService;
import com.slavetny.quwitest.domain.model.Name;
import com.slavetny.quwitest.domain.model.login.Login;
import com.slavetny.quwitest.domain.model.project.Project;
import com.slavetny.quwitest.domain.model.login.Token;
import javax.inject.Inject;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private final QuwiService quwiService;

    @Inject
    public RepositoryImpl(QuwiService quwiService) {
        this.quwiService = quwiService;
    }

    @Override
    public MutableLiveData<String> getToken(Login login) {
        MutableLiveData<String> tokenLiveData = new MutableLiveData<>();

        Token token = quwiService
                .getToken(login)
                .subscribeOn(Schedulers.io())
                .blockingSingle();

        tokenLiveData.setValue(token.getToken());

        return tokenLiveData;
    }

    @Override
    public MutableLiveData<Project> getProjectList(String token) {
        MutableLiveData<Project> projectLiveData = new MutableLiveData<>();

        Project project = quwiService
                .getProjectList("Bearer " + token)
                .subscribeOn(Schedulers.io())
                .blockingSingle();

        projectLiveData.setValue(project);

        return projectLiveData;
    }

    @Override
    public MutableLiveData<Project> updateProjectName(int projectId, String projectName, String token) {
        MutableLiveData<Project> projectLiveData = new MutableLiveData<>();

        Name name = new Name();
        name.setName(projectName);

        Project project = quwiService
                .updateProjectName(projectId, name, "Bearer " + token)
                .subscribeOn(Schedulers.io())
                .blockingSingle();

        projectLiveData.setValue(project);

        return projectLiveData;
    }
}