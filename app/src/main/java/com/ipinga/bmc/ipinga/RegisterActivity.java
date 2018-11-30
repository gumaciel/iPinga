package com.ipinga.bmc.ipinga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class RegisterActivity extends AppCompatActivity {

    private EditText editNomeCad, editEmailCad, editSenhaCad, editSenhaConf;
    private Button btnRegister;

    private String HOST = "http://192.168.0.145/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNomeCad = (EditText) findViewById(R.id.editNomeCad);
        editEmailCad = (EditText) findViewById(R.id.editEmailCad);
        editSenhaCad = (EditText) findViewById(R.id.editSenhaCad);
        editSenhaConf = (EditText) findViewById(R.id.editSenhaConf);

        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editNomeCad.getText().toString();
                String email = editEmailCad.getText().toString();
                String senha = editSenhaCad.getText().toString();
                String confirma = editSenhaConf.getText().toString();

                String URL = HOST + "/cadastrar.php";

                if (confirma.equals(senha)) {
                    if (!nome.isEmpty() || !email.isEmpty() || !senha.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Todos os campos são obrigatórios.", Toast.LENGTH_LONG).show();
                    } else {
                        Ion.with(RegisterActivity.this)
                                .load(URL)
                                .setBodyParameter("nome_app", nome)
                                .setBodyParameter("email_app", email)
                                .setBodyParameter("senha_app", senha)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {

                                        try {
                                            //Toast.makeText(RegisterActivity.this, "Retorno: " + result.toString(), Toast.LENGTH_LONG).show();
                                            String RETORNO = result.get("CADASTRO").getAsString();

                                            if (RETORNO.equals("EMAIL_ERRO")){
                                                Toast.makeText(RegisterActivity.this, "Ops! Email já cadastrado.", Toast.LENGTH_LONG).show();
                                            } else if (RETORNO.equals("SUCESSO")){
                                                //Toast.makeText(RegisterActivity.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                                                Intent abrePrincipal = new Intent(RegisterActivity.this, MenuActivity.class);
                                                startActivity(abrePrincipal);
                                            } else {
                                                Toast.makeText(RegisterActivity.this, "Ops! Ocorreu um erro.", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {
                                            Toast.makeText(RegisterActivity.this, "Ops! Ocorreu um erro, " + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "A senha não confere.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
