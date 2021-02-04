package com.slavetny.quwitest.di;

import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.usecase.GetProjectListUseCase;
import com.slavetny.quwitest.domain.usecase.LoginUseCase;
import com.slavetny.quwitest.domain.usecase.UpdateProjectNameUseCase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.scopes.FragmentScoped;

@Module
@InstallIn(FragmentComponent.class)
public class UseCaseModule {
    @Provides
    @FragmentScoped
    LoginUseCase provideLoginUseCase(RepositoryImpl repository) {
        return new LoginUseCase(repository);
    }

    @Provides
    @FragmentScoped
    GetProjectListUseCase provideGetProjectListUseCase(RepositoryImpl repository) {
        return new GetProjectListUseCase(repository);
    }

    @Provides
    @FragmentScoped
    UpdateProjectNameUseCase provideUpdateProjectNameUseCase(RepositoryImpl repository) {
        return new UpdateProjectNameUseCase(repository);
    }
}