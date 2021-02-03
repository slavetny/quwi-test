package com.slavetny.quwitest.data.network;

import com.slavetny.quwitest.domain.model.Login;
import com.slavetny.quwitest.domain.model.Token;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface QuwiService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Observable<Token> login(@Body Login login);
}