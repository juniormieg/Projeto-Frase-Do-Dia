package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class GeradorDeFrases {
    private final String[] frases = {
            "Boas notícias: o professor decidiu dar 10 para todos os projetos de hoje. É o famoso 'milagre do código'!\" \uD83C\uDF89",
            "Hoje o café acabou antes da vontade de trabalhar. Coincidência? Acho que não!",
            "Não deixe para amanhã o que você pode fingir que esqueceu hoje.",
            "O objetivo do dia é simples: sobreviver sem parecer que estou apenas sobrevivendo.",
            "Se errar é humano, então eu sou praticamente um super-herói da humanidade!",
            "Nunca subestime o poder de uma pausa para café... ou duas... ou três.",
            "Cuidado: sexta-feira não significa que o problema da segunda desapareceu.",
            "A vida é curta demais para abrir e-mail na sexta-feira."
    };

    public String[] getFrases() {
        return this.frases;
    }

    public String aleatorizarFrase() {
        int index = (int) (Math.random() * frases.length);
        return frases[index];
    }
}

public class MainActivity extends AppCompatActivity {

    private boolean frasesVisiveis = false;
    private GeradorDeFrases fraseEscolhida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fraseEscolhida = new GeradorDeFrases();

        TextView fraseDoDia = findViewById(R.id.fraseDoDia);
        TextView exibirFrasesDoDia = findViewById(R.id.todasAsFrases);
        Button gerador = findViewById(R.id.botaoGerador);
        Button todasAsFrases = findViewById(R.id.botaoDeFrases);

        gerador.setOnClickListener(view -> {
            String fraseAleatoria = fraseEscolhida.aleatorizarFrase();
            fraseDoDia.setText(fraseAleatoria);

        });

        todasAsFrases.setOnClickListener(view -> {
            if (frasesVisiveis) {
                exibirFrasesDoDia.setText("");
                todasAsFrases.setText("Mostrar todas as frases");
                frasesVisiveis = false;
            } else {
                String[] frases = fraseEscolhida.getFrases();
                String todasAsFrasesDoDia = String.join("\n", frases);
                exibirFrasesDoDia.setText(todasAsFrasesDoDia);
                todasAsFrases.setText("Esconder as frases");
                frasesVisiveis = true;
            }
        });
    }
}
