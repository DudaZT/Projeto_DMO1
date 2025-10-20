package com.example.guiapocket_bairrovilaxavier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.guiapocket_bairrovilaxavier.databinding.ItemServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class ServicoAdapter(
    private val context: Context,
    private val servicos: List<Servico>
) : ArrayAdapter<Servico>(context, 0, servicos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemServicoBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemServicoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemServicoBinding
        }

        val servico = servicos[position]

        binding.imgFoto.setImageResource(servico.imagem)
        binding.tvNome.text = servico.nome
        binding.tvCategoria.text = servico.categoria

        return itemView
    }
}