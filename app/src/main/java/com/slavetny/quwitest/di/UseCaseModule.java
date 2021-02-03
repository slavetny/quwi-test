package com.slavetny.quwitest.di;

import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.LoginUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class UseCaseModule {
    @Provides
    @ViewModelScoped
    LoginUseCase provideLoginUseCase(RepositoryImpl repository) {
        return new LoginUseCase(repository);
    }
}