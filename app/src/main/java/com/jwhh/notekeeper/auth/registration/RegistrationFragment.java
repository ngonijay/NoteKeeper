package com.jwhh.notekeeper.auth.registration;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jwhh.notekeeper.NoteListActivity;
import com.jwhh.notekeeper.R;

public class RegistrationFragment extends Fragment {

    private EditText email, password;
    private Button register;

    private FirebaseAuth mAuth;
    private RegistrationViewModel mViewModel;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.registration_fragment, container, false);
        initializeView(root);
        return root;
    }

    private void initializeView(View root) {
        email = root.findViewById(R.id.editTextEmailAddress);
        password = root.findViewById(R.id.editTextPassword);
        register = root.findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                if (TextUtils.isEmpty(textEmail) || TextUtils.isEmpty(textPassword))
                    Toast.makeText(getContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                else if (textPassword.length() < 6)
                    Toast.makeText(getContext(), "Password too short", Toast.LENGTH_SHORT).show();
                else
                    registerUser(textEmail, textPassword);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        // TODO: Use the ViewModel
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), NoteListActivity.class));
                } else
                    Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}