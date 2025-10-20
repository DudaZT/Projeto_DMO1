package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityDetalheServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class DetalheServicoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheServicoBinding
    private lateinit var servico: Servico

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData()
    {
        servico = intent.getSerializableExtra("servico") as Servico
    }

    private fun setupViews() {
        binding.imgDetalheFoto.setImageResource(servico.imagem)
        binding.tvDetalheNome.text = servico.nome
        binding.tvDetalheCategoria.text = servico.categoria
        binding.tvDetalheDescricao.text = servico.descricao

        // Formatar os textos com labels
        binding.tvDetalheEndereco.text = "${getString(R.string.address)}: ${servico.endereco}"
        binding.tvDetalheTelefone.text = "${getString(R.string.phone)}: ${servico.telefone}"
        binding.tvDetalheWebsite.text = "${getString(R.string.website)}: ${servico.website}"

        // Adicionar ícones
        binding.tvDetalheEndereco.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location, 0, 0, 0)
        binding.tvDetalheTelefone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call, 0, 0, 0)
        binding.tvDetalheWebsite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_website, 0, 0, 0)
    }

    private fun setupListeners() {
        binding.btnVoltar.setOnClickListener {
            finish()
        }

        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:${servico.telefone}".toUri()
            startActivity(intent)
        }

        binding.btnSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "${servico.website}".toUri()
            startActivity(intent)
        }

        binding.btnMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "geo:0,0?q=${Uri.encode(servico.endereco)}".toUri()
            startActivity(intent)
        }

        binding.btnCompartilhar.setOnClickListener {
            val shareText = "${servico.nome}\n${servico.descricao}\n${servico.telefone}\n${servico.endereco}"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(intent, getString(R.string.share_service)))
        }
    }
}