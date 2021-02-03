package com.slavetny.quwitest.domain;

import androidx.lifecycle.MutableLiveData;

import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.model.Login;

import javax.inject.Inject;

public class LoginUseCase {

    RepositoryImpl repository;

    @Inject
    public LoginUseCase(RepositoryImpl repository) {
        this.repository = repository;
    }

    public MutableLiveData<String> getLoginToken(Login login) {
        return repository.login(login);
    }
}