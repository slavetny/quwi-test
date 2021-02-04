package com.slavetny.quwitest.data.network;

import com.slavetny.quwitest.domain.model.Name;
import com.slavetny.quwitest.domain.model.login.Login;
import com.slavetny.quwitest.domain.model.project.Project;
import com.slavetny.quwitest.domain.model.login.Token;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface QuwiService {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Observable<Token> getToken(@Body Login login);

    @Headers("Content-Type: application/json")
    @GET("projects-manage/index")
    Observable<Project> getProjectList(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("projects-manage/update")
    Observable<Project> updateProjectName(@Query("id") int id, @Body Name name, @Header("Authorization") String token);

}