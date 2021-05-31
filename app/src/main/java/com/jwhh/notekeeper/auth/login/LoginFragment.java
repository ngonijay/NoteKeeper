package com.jwhh.notekeeper.auth.login;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jwhh.notekeeper.NoteListActivity;
import com.jwhh.notekeeper.R;
import com.jwhh.notekeeper.auth.registration.RegistrationFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private EditText email, password;
    private Button register, login;
    private FirebaseAuth mFirebaseAuth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        mFirebaseAuth = FirebaseAuth.getInstance();
        initializeView(root);
        return root;
    }

    private void initializeView(View root) {
        email = root.findViewById(R.id.editTextEmailAddress);
        password = root.findViewById(R.id.editTextPassword);
        register = root.findViewById(R.id.registerBtn);
        login = root.findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                loginUser(textEmail, textPassword);
            }

        });

        register.setOnClickListener(view -> {

            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.authFrameLayout, new RegistrationFragment(), "RegistrationFragment");
            ft.commit();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    private void loginUser(String email, String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), NoteListActivity.class));
            }
        });
    }

}