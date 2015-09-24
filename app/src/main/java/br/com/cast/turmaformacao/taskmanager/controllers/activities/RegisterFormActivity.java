package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.Http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.services.LoginBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class RegisterFormActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextPasswordConfirm;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;

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
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextZipCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_action_toggle_check_box, 0);
        editTextZipCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextZipCode.getRight() - editTextZipCode.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        new GetAddressTask().execute(editTextZipCode.getText().toString());
                    }
                }
                return false;
            }
        });
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextState = (EditText) findViewById(R.id.editTextState);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCamposObrigatorios() && validarSenhas()) {
                    bindUser();
                    LoginBusinessService.save(user);
                    Toast.makeText(RegisterFormActivity.this, R.string.msg_save_success, Toast.LENGTH_SHORT).show();
                    Intent redirectToTaskList = new Intent(RegisterFormActivity.this, LoginActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                } else{
                    Toast.makeText(RegisterFormActivity.this, R.string.msg_password_doesnt_match, Toast.LENGTH_LONG);
                    editTextPassword.setText("");
                    editTextPasswordConfirm.setText("");
                }
            }
        });
    }

    private boolean validarSenhas() {
        return editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString());
    }

    private boolean validarCamposObrigatorios() {
        return !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextLogin) &&
                !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextPassword) &&
                !FormHelper.checkRequireFields(getString(R.string.msg_required), editTextPasswordConfirm);
    }

    private void bindUser() {
        user = new User();
        user.setName(editTextLogin.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
    }

    private class GetAddressTask extends AsyncTask<String, Void, Address> {

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
}
