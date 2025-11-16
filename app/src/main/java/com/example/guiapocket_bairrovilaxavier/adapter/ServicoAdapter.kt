package com.example.guiapocket_bairrovilaxavier.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guiapocket_bairrovilaxavier.databinding.ItemServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class ServicoAdapter(
    private val servicos: List<Servico>,
    private val onItemClick: (Servico) -> Unit
) : RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    inner class ServicoViewHolder(private val binding: ItemServicoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(servico: Servico) {
            binding.imgFoto.setImageResource(servico.imagem)
            binding.tvNome.text = servico.nome
            binding.tvCategoria.text = servico.categoria

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
}