package br.com.fecapccp.ni_gabrielcarvalhomota;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AbaixoDoPesoActivity extends AppCompatActivity {

    private Button btnFechar;
    private TextView textViewIMC;
    private TextView textViewPeso;
    private TextView textViewAltura;
    private TextView textViewClassificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abaixo_do_peso);

        btnFechar = findViewById(R.id.btnFechar);
        textViewIMC = findViewById(R.id.textViewIMC);
        textViewPeso = findViewById(R.id.textViewPeso);
        textViewAltura = findViewById(R.id.textViewAltura);
        textViewClassificacao = findViewById(R.id.textViewClassificacao);

        // Recebendo os dados
        Bundle bundle = getIntent().getExtras();

        double peso = bundle.getDouble("peso", 0.0);
        double altura = bundle.getDouble("altura", 0.0);
        double resultado = bundle.getDouble("resultado", 0.0);
        String classificacao = bundle.getString("classificacao", "");

        // Definindo os valores nos TextView com formatação
        textViewIMC.setText(String.format("IMC: %.2f", resultado));
        textViewPeso.setText(String.format("Peso: %.2f kg", peso));
        textViewAltura.setText(String.format("Altura: %.2f m", altura));
        textViewClassificacao.setText(classificacao);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnFechar.setOnClickListener(v -> {
            Intent intent = new Intent(AbaixoDoPesoActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);  // Limpa a pilha e abre a MainActivity
            startActivity(intent);
            finish();
        });
    }
}