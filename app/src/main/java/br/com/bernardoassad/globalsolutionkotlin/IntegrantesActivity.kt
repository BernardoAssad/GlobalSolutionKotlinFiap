package br.com.bernardoassad.globalsolutionkotlin

// Importações necessárias
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// Definição da classe IntegrantesActivity que herda de AppCompatActivity
class IntegrantesActivity : AppCompatActivity() {

    // Função chamada quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integrantes) // Define o layout da atividade

        // Pega uma referência para o botão "backButton" do layout
        val backButton: Button = findViewById(R.id.backButton)

        // Configura um listener para o clique no botão "backButton"
        backButton.setOnClickListener {
            finish() // Finaliza a atividade quando o botão é clicado
        }
    }
}
