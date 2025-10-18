package com.example.guiapocket_bairrovilaxavier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.model.Servico

class ServicoAdapter(context: Context, private val servicos: List<Servico>) :
    ArrayAdapter<Servico>(context, 0, servicos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_servico, parent, false)

        val servico = servicos[position]

        val img = view.findViewById<ImageView>(R.id.imgFoto)
        val nome = view.findViewById<TextView>(R.id.tvNome)
        val categoria = view.findViewById<TextView>(R.id.tvCategoria)

        img.setImageResource(servico.imagem)
        nome.text = servico.nome
        categoria.text = servico.categoria

        return view
    }
}