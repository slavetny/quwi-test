package com.slavetny.quwitest.di;

import com.slavetny.quwitest.data.network.QuwiService;
import com.slavetny.quwitest.data.repository.RepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {
    @Provides
    @ViewModelScoped
    RepositoryImpl provideRepository(QuwiService quwiService) {
        return new RepositoryImpl(quwiService);
    }
}