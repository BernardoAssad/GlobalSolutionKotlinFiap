package br.com.bernardoassad.globalsolutionkotlin

// Importações necessárias
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Classe que adapta os dados das praias para exibição em um RecyclerView
class BeachAdapter(private val beaches: MutableList<Beach>, private val onDeleteClick: (Beach) -> Unit) :
    RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {

    // Classe interna que representa a visualização de cada item de praia
    class BeachViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beachTextView: TextView = view.findViewById(R.id.beachTextView) // TextView para exibir o nome da praia
        val deleteButton: Button = view.findViewById(R.id.deleteButton) // Botão para excluir a praia
    }

    // Função chamada quando um novo ViewHolder precisa ser criado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
        // Infla o layout do item de praia e cria uma nova instância de BeachViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_beach, parent, false)
        return BeachViewHolder(view)
    }

    // Função chamada quando um ViewHolder precisa ser vinculado a dados específicos
    override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
        val beach = beaches[position] // Obtém a praia na posição atual
        // Define o texto da TextView para exibir o nome, cidade e estado da praia
        holder.beachTextView.text = buildString {
            append(beach.nome)
            append(" - ")
            append(beach.cidade)
            append("/")
            append(beach.estado)
        }
        // Configura um listener para o clique no botão de exclusão
        holder.deleteButton.setOnClickListener {
            onDeleteClick(beach) // Chama a função onDeleteClick passando a praia como parâmetro
        }
    }

    // Função que retorna a quantidade total de praias na lista
    override fun getItemCount() = beaches.size
}
