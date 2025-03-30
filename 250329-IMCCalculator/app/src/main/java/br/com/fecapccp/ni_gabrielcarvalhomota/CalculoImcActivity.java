package br.com.fecapccp.ni_gabrielcarvalhomota;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoImcActivity extends AppCompatActivity {

    private Button btnFechar;
    private Button btnLimpar;
    private Button btnCalcularIMC;
    private EditText editTextPeso;
    private EditText editTextAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imc);

        btnFechar = findViewById(R.id.btnFechar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcularIMC = findViewById(R.id.btnCalcularIMC);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextAltura = findViewById(R.id.editTextAltura);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnFechar.setOnClickListener(v -> {
            finish();
        });

        btnLimpar.setOnClickListener(v -> {
            editTextPeso.setText("");
            editTextAltura.setText("");
        });

        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double peso = Double.parseDouble(editTextPeso.getText().toString());
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double resultado = peso / (altura * altura);
                String classificacao = "";

                Intent intent = null;

                // Definindo a classificação com base no resultado do IMC
                if (resultado < 18.5) {
                    classificacao = "Abaixo do peso";
                    intent = new Intent(CalculoImcActivity.this, AbaixoDoPesoActivity.class);
                } else if (resultado >= 18.5 && resultado < 25) {
                    classificacao = "Peso normal";
                    intent = new Intent(CalculoImcActivity.this, PesoNormalActivity.class);
                } else if (resultado >= 25 && resultado < 30) {
                    classificacao = "Sobrepeso";
                    intent = new Intent(CalculoImcActivity.this, SobrepesoActivity.class);
                } else if (resultado >= 30 && resultado < 35) {
                    classificacao = "Obesidade grau 1";
                    intent = new Intent(CalculoImcActivity.this, Obesidade1Activity.class);
                } else if (resultado >= 35 && resultado < 40) {
                    classificacao = "Obesidade grau 2";
                    intent = new Intent(CalculoImcActivity.this, Obesidade2Activity.class);
                } else {
                    classificacao = "Obesidade grau 3";
                    intent = new Intent(CalculoImcActivity.this, Obesidade3Activity.class);
                }

                // Transferindo os valores para a próxima Activity
                intent.putExtra("peso", peso);
                intent.putExtra("altura", altura);
                intent.putExtra("resultado", resultado);
                intent.putExtra("classificacao", classificacao);

                startActivity(intent);

            }
        });
    }
}