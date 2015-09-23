package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.services.LoginBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class RegisterFormActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextPasswordConfirm;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        bindButtonRegister();
    }

    private void bindButtonRegister() {
        buttonRegister = (Button) findViewById(R.id.buttonUserRegister );
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    bindUser();
                    LoginBusinessService.save(user);
                    Toast.makeText(RegisterFormActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                    Intent redirectToTaskList = new Intent(RegisterFormActivity.this, LoginActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                }
            }
        });
    }

    private boolean validarCampos() {
        return !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextLogin) &&
                !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextPassword) &&
                !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextPasswordConfirm);
    }

    private void bindUser() {
        user = new User();
        user.setName(editTextLogin.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
    }
}
