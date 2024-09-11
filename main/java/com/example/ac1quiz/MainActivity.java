package com.example.ac1quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvArea, tvQuestion, tvFeedback, tvScore;
    private RadioGroup rgOptions;
    private Button btnValidate, btnReset;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando Views
        tvArea = findViewById(R.id.tvArea);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        tvFeedback = findViewById(R.id.tvFeedback);
        tvScore = findViewById(R.id.tvScore);
        btnValidate = findViewById(R.id.btnValidate);
        btnReset = findViewById(R.id.btnReset);

        // Inicializando perguntas
        questions = new ArrayList<>();
        questions.add(new Question("Matemática", "Qual é o resultado de 7 + 8?", new String[]{"5", "27", "15", "16", "11"}, 2));
        questions.add(new Question("História", "Qual é a capital do Brasil?", new String[]{"Brasilia", "São Paulo", "Bahia", "Belo Horizonte", "Maranhão"}, 0));
        questions.add(new Question("Geografia", "Qual é o maior país do mundo?", new String[]{"Brasil", "China", "Rússia", "Canadá", "EUA"}, 1));

        // Exibir primeira pergunta
        displayQuestion();

        // Configurar o botão de validar
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer();
            }
        });

        // Configurar o botão de reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetQuiz();
            }
        });
    }

    // Exibir a questão atual
    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        tvArea.setText("Área: " + currentQuestion.getArea());
        tvQuestion.setText(currentQuestion.getQuestion());

        // Exibir opções
        ((RadioButton) rgOptions.getChildAt(0)).setText(currentQuestion.getOptions()[0]);
        ((RadioButton) rgOptions.getChildAt(1)).setText(currentQuestion.getOptions()[1]);
        ((RadioButton) rgOptions.getChildAt(2)).setText(currentQuestion.getOptions()[2]);
        ((RadioButton) rgOptions.getChildAt(3)).setText(currentQuestion.getOptions()[3]);
        ((RadioButton) rgOptions.getChildAt(4)).setText(currentQuestion.getOptions()[4]);

        // Limpar seleção anterior
        rgOptions.clearCheck();
        tvFeedback.setText("");
    }

    // Validar resposta
    private void validateAnswer() {
        int selectedOptionIndex = rgOptions.indexOfChild(findViewById(rgOptions.getCheckedRadioButtonId()));
        if (selectedOptionIndex == -1) {
            tvFeedback.setText("Por favor, selecione uma resposta.");
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedOptionIndex == currentQuestion.getCorrectAnswerIndex()) {
            tvFeedback.setText("Correto!");
            score++;
        } else {
            tvFeedback.setText("Errado. A resposta correta é: " + currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()]);
        }

        tvScore.setText("Pontuação: " + score);

        // Avançar para a próxima pergunta, se houver
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            btnValidate.setEnabled(false);
            tvFeedback.setText("Fim do quiz! Pontuação final: " + score + "/" + questions.size());
        }
    }

    // Reiniciar o quiz
    private void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        btnValidate.setEnabled(true);
        displayQuestion();
        tvScore.setText("Pontuação: " + score);
        tvFeedback.setText("");
    }
}


