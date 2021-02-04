package com.slavetny.quwitest.presentation.screen.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.slavetny.quwitest.R;
import com.slavetny.quwitest.domain.usecase.LoginUseCase;
import com.slavetny.quwitest.domain.constants.Constants;
import com.slavetny.quwitest.domain.model.login.Login;
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

        if (isTokenExist()) {
            navigateToProjectList();
        }

        EditText loginEditText = view.findViewById(R.id.login_field);
        EditText passwordEditText = view.findViewById(R.id.password_field);
        Button loginButton = view.findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            try {
                Login login = getLoginBody(
                        loginEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );

                setTokenObserver(login);
            } catch (Exception e) {
                Toast.makeText(requireContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTokenObserver(Login login) {
        loginUseCase.getLoginToken(login).observe(getViewLifecycleOwner(), token -> {
            saveToken(token);
            navigateToProjectList();
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.TOKEN, token);
        editor.apply();
    }

    private boolean isTokenExist() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE);
        return !sharedPref.getString(Constants.TOKEN, Constants.NO_VALUE).equals(Constants.NO_VALUE);
    }

    private void navigateToProjectList() {
        Navigation.findNavController(requireView()).navigate(R.id.action_login_to_project_list);
    }

    private Login getLoginBody(String login, String password) throws Exception {
        if (!isValidEmail(login)) {
            throw new Exception("Uncorrected Email address");
        } else if (!isValidPassword(password)) {
            throw new Exception("Uncorrected Password");
        } else {
            return new Login(login, password);
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target)) && target.length() >= 5;
    }
}