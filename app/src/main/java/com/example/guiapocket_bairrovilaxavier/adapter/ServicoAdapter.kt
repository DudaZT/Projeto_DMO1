package com.example.guiapocket_bairrovilaxavier.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guiapocket_bairrovilaxavier.databinding.ItemServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class ServicoAdapter(
    private var servicos: List<Servico>, // Lista atual de serviços
    private val onItemClick: (Servico) -> Unit // Função chamada quando clica no item
) : RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    // ViewHolder representa cada card da lista
    inner class ServicoViewHolder(private val binding: ItemServicoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(servico: Servico) {
            // Carrega a imagem direto da URI salva no banco
            binding.imgFoto.setImageURI(servico.imagem)
            binding.tvNome.text = servico.nome
            binding.tvCategoria.text = servico.categoria

            // Clique do item -> manda o serviço pra Activity de detalhes
            binding.root.setOnClickListener {
                onItemClick(servico)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val binding = ItemServicoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServicoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        holder.bind(servicos[position])
    }

    override fun getItemCount(): Int = servicos.size

    fun updateLista(novosServicos: List<Servico>) {
        // Atualiza a lista (ex: após filtro)
        this.servicos = novosServicos
        notifyDataSetChanged()
    }
}