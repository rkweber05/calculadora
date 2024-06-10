package com.example.calculatorfeevale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado;
    Button btDivisao;
    Button btMultiplicacao;
    Button btAdicao;
    Button btSubtracao;
    Button btZero;
    Button btUm;
    Button btDois;
    Button btTres;
    Button btQuatro;
    Button btCinco;
    Button btSeis;
    Button btSete;
    Button btOito;
    Button btNove;
    Button btLimpar;
    Button btHistorico;
    Button btPonto;
    Button btIgual;

    List<String> historicoCalculos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(android.R.color.white));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botões números
        btZero = findViewById(R.id.btNumeroZero);
        btUm = findViewById(R.id.btNumeroUm);
        btDois = findViewById(R.id.btNumeroDois);
        btTres = findViewById(R.id.btNumeroTres);
        btQuatro = findViewById(R.id.btNumeroQuatro);
        btCinco = findViewById(R.id.btNumeroCinco);
        btSeis = findViewById(R.id.btNumeroSeis);
        btSete = findViewById(R.id.btNumeroSete);
        btOito = findViewById(R.id.btNumeroOito);
        btNove = findViewById(R.id.btNumeroNove);

        // Botões funções
        btSubtracao = findViewById(R.id.btSubtracao);
        btAdicao = findViewById(R.id.btAdicao);
        btMultiplicacao = findViewById(R.id.btMultiplicacao);
        btDivisao = findViewById(R.id.btDivisao);
        btDivisao.setText("/");

        // Limpar e Histórico
        btLimpar = findViewById(R.id.btClear);
        btHistorico = findViewById(R.id.btHistorico);

        // Ponto e igual
        btPonto = findViewById(R.id.btPonto);
        btIgual = findViewById(R.id.btIgual);

        // Texto resultado
        txtResultado = findViewById(R.id.txtResultado);
        txtResultado.setText("");

        btHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
                // Adiciona o histórico como um extra na Intent
                intent.putStringArrayListExtra("historico", new ArrayList<>(historicoCalculos));
                startActivity(intent);
            }
        });

        // Clique 0 a 9
        btZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("0");
            }
        });

        btUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("1");
            }
        });

        btDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("2");
            }
        });

        btTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("3");
            }
        });

        btQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("4");
            }
        });

        btCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("5");
            }
        });

        btSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("6");
            }
        });

        btSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("7");
            }
        });

        btOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("8");
            }
        });

        btNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirNumero("9");
            }
        });

        // Clique funções
        btAdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirOperador("+");
            }
        });

        btSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirOperador("-");
            }
        });

        btMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirOperador("*");
            }
        });

        btDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirOperador("/");
            }
        });

        // Clique Clear, ponto e igual
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCalculadora();
            }
        });

        btPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarPonto();
            }
        });

        btIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarCalculo();
            }
        });
    }

    private void inserirNumero(String number) {
        String currentText = txtResultado.getText().toString();
        txtResultado.setText(currentText + number);
    }

    private void inserirOperador(String operation) {
        String currentText = txtResultado.getText().toString();
        if (currentText.isEmpty() || ultimoOperadorInserido(currentText)) {
            return;
        }
        txtResultado.setText(currentText + operation);
    }

    private boolean ultimoOperadorInserido(String text) {
        if (text.isEmpty()) {
            return false;
        }
        char lastChar = text.charAt(text.length() - 1);
        return lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/';
    }

    private void limparCalculadora() {
        txtResultado.setText("");
    }

    private void adicionarPonto() {
        String currentText = txtResultado.getText().toString();
        if (currentText.isEmpty() || currentText.charAt(currentText.length() - 1) != '.') {
            txtResultado.setText(currentText + ".");
        }
    }

    private void realizarCalculo() {
        String expressao = txtResultado.getText().toString();
        try {
            Expression exp = new ExpressionBuilder(expressao).build();
            double resultado = exp.evaluate();
            txtResultado.setText(Double.toString(resultado));

            // Adiciona a expressão ao histórico de cálculos
            historicoCalculos.add(expressao + " = " + resultado);
        } catch (Exception e) {  // catch a more general exception to cover more cases
            txtResultado.setText("Erro");
        }
    }
}
