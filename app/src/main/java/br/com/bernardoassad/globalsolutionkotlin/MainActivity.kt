package br.com.bernardoassad.globalsolutionkotlin

// Importações necessárias
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Definição da classe MainActivity que herda de AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declarando variáveis para os elementos da interface do usuário
    private lateinit var nomePraiaEditText: EditText
    private lateinit var cidadeEditText: EditText
    private lateinit var estadoEditText: EditText
    private lateinit var incluirButton: Button
    private lateinit var beachRecyclerView: RecyclerView
    private lateinit var beachAdapter: BeachAdapter
    private lateinit var integrantesButton: Button
    private lateinit var excluirTodosButton: Button

    // Lista mutável para armazenar as praias
    private val beaches = mutableListOf<Beach>()

    // Função chamada quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos elementos da interface
        nomePraiaEditText = findViewById(R.id.nomePraiaEditText)
        cidadeEditText = findViewById(R.id.cidadeEditText)
        estadoEditText = findViewById(R.id.estadoEditText)
        incluirButton = findViewById(R.id.incluirButton)
        beachRecyclerView = findViewById(R.id.beachRecyclerView)
        integrantesButton = findViewById(R.id.integrantesButton)
        excluirTodosButton = findViewById(R.id.excluirTodosButton)

        // Configurando o RecyclerView
        beachAdapter = BeachAdapter(beaches) { beach ->
            beaches.remove(beach)
            beachAdapter.notifyDataSetChanged()
        }
        beachRecyclerView.layoutManager = LinearLayoutManager(this)
        beachRecyclerView.adapter = beachAdapter

        // Configurando o botão de inclusão de praia
        incluirButton.setOnClickListener {
            addBeach()
        }

        // Configurando o botão para ver os integrantes
        integrantesButton.setOnClickListener {
            val intent = Intent(this, IntegrantesActivity::class.java)
            startActivity(intent)
        }

        // Configurando o botão para excluir todas as praias
        excluirTodosButton.setOnClickListener {
            removeAllBeaches()
        }
    }

    // Função para adicionar uma praia à lista
    private fun addBeach() {
        // Obtendo os valores dos EditText
        val nome = nomePraiaEditText.text.toString().trim()
        val cidade = cidadeEditText.text.toString().trim()
        val estado = estadoEditText.text.toString().trim()

        // Verificando se os campos estão preenchidos
        if (nome.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_campos_obrigatorios), Toast.LENGTH_SHORT).show()
            return
        }

        // Verificando se os campos possuem tamanho mínimo
        if (nome.length < 3 || cidade.length < 3 || estado.length < 3) {
            Toast.makeText(this, getString(R.string.error_minimo_caracteres), Toast.LENGTH_SHORT).show()
            return
        }

        // Criando um objeto Beach e adicionando à lista
        val beach = Beach(nome, cidade, estado)
        beaches.add(beach)
        beachAdapter.notifyDataSetChanged()

        // Limpando os EditText após a inclusão
        nomePraiaEditText.text.clear()
        cidadeEditText.text.clear()
        estadoEditText.text.clear()
    }

    // Função para remover todas as praias da lista
    private fun removeAllBeaches() {
        beaches.clear()
        beachAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Todas as praias foram removidas", Toast.LENGTH_SHORT).show()
    }
}
