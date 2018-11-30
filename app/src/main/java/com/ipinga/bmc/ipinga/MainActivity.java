package com.ipinga.bmc.ipinga;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends Activity {

    private EditText editEmailLogar, editSenhaLogar;
    private Button btnEntrar, btnRegister;

    private String HOST = "http://192.168.0.145/login";

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmailLogar = (EditText) findViewById(R.id.editEmailLogar);
        editSenhaLogar = (EditText) findViewById(R.id.editEmailLogar);

        btnRegister = (Button) findViewById(R.id.btnRegister);

            Button cadastrar = (Button) findViewById(R.id.btn_cadastrar);
            cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.activity_register);
                }
        });

            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = editEmailLogar.getText().toString();
                    String senha = editSenhaLogar.getText().toString();

                    String URL = HOST + "/logar.php";

                    if (!email.isEmpty() || !senha.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Todos os campos são obrigatórios.", Toast.LENGTH_LONG).show();
                    } else {
                        Ion.with(MainActivity.this)
                                .load(URL)
                                .setBodyParameter("email_app", email)
                                .setBodyParameter("senha_app", senha)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {

                                        try {
                                            //Toast.makeText(MainActivity.this, "Retorno: " + result.toString(), Toast.LENGTH_LONG).show();
                                            String RETORNO = result.get("LOGIN").getAsString();

                                            if (RETORNO.equals("ERRO")){
                                                Toast.makeText(MainActivity.this, "Ops! Email ou senha incorreto.", Toast.LENGTH_LONG).show();
                                            } else if (RETORNO.equals("SUCESSO")){
                                                Intent abrePrincipal = new Intent(MainActivity.this, MenuActivity.class);
                                                startActivity(abrePrincipal);
                                            } else {
                                                Toast.makeText(MainActivity.this, "Ops! Ocorreu um erro.", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {
                                            Toast.makeText(MainActivity.this, "Ops! Ocorreu um erro, " + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                }
            });

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
