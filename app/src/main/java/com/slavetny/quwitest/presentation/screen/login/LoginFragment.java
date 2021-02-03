package com.slavetny.quwitest.presentation.screen.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.slavetny.quwitest.R;
import com.slavetny.quwitest.data.repository.RepositoryImpl;
import com.slavetny.quwitest.domain.LoginUseCase;
import com.slavetny.quwitest.domain.model.Login;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    @Inject
    LoginUseCase loginUseCase;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Login login = new Login();
        login.setEmail("vitaliibondtest@gmail.com");
        login.setPassword("vitaliibondtest");

        loginUseCase.getLoginToken(login).observe(getViewLifecycleOwner(), s ->
                Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
        );
    }
}