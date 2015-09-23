package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.Http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;

public class LoginActivity extends AppCompatActivity {

    private List<User> users;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
        bindUsers();
        bindButtonRegister();

        new GetAddressTask().execute("15993026");
    }

    private class GetAddressTask extends AsyncTask<String, Void, Address>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByCep(params[0]);
        }
    }

    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarUsuario(users)) {
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setMessage(R.string.validate_error)
                            .setTitle(R.string.message)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    editTextLogin.setText("");
                                    editTextPassword.setText("");
                                }
                            })
                            .create()
                            .show();
                }
            }
        });
    }

    private void bindButtonRegister() {
        buttonRegister = (Button) findViewById(R.id.buttonUserRegister );

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToTaskList = new Intent(LoginActivity.this, RegisterFormActivity.class);
                startActivity(redirectToTaskList);
                finish();
            }
        });
    }

    private boolean validarUsuario(List<User> users) {
        for (User item : users){
            if( item.getName().equals(editTextLogin.getText().toString()) &&
                    item.getPassword().equals(editTextPassword.getText().toString())){
                return true;
            }
        }
        return false;
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

    private void bindUsers() {
        users = User.getAll();
    }
}
