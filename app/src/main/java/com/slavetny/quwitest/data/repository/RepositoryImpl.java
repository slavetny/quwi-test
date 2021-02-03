package com.slavetny.quwitest.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.slavetny.quwitest.data.network.QuwiService;
import com.slavetny.quwitest.domain.model.Login;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryImpl {

    private final QuwiService quwiService;

    @Inject
    public RepositoryImpl(QuwiService quwiService) {
        this.quwiService = quwiService;
    }

    public MutableLiveData<String> login(Login login) {
        MutableLiveData<String> tokenLiveData = new MutableLiveData<>();

        quwiService
                .login(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(token -> {
                    tokenLiveData.setValue(token.getToken());
                });

        return tokenLiveData;
    }
}