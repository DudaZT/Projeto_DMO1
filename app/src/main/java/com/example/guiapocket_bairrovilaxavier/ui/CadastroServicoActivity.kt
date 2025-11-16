package com.example.guiapocket_bairrovilaxavier.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityCadastroServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class CadastroServicoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            if (validarCampos()) {
                salvarServico()
            }
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        val nome = binding.etNome.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val descricao = binding.etDescricao.text.toString().trim()
        val endereco = binding.etEndereco.text.toString().trim()
        val telefone = binding.etTelefone.text.toString().trim()
        val website = binding.etWebsite.text.toString().trim()

        if (nome.isEmpty()) {
            binding.etNome.error = "Nome é obrigatório"
            return false
        }

        if (categoria.isEmpty()) {
            binding.etCategoria.error = "Categoria é obrigatória"
            return false
        }

        return true
    }

    private fun salvarServico() {
        val nome = binding.etNome.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val descricao = binding.etDescricao.text.toString().trim()
        val endereco = binding.etEndereco.text.toString().trim()
        val telefone = binding.etTelefone.text.toString().trim()
        val website = binding.etWebsite.text.toString().trim()

        val imagemPadrao = R.drawable.pipocopos

        val novoId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
        val servico = Servico(
            id = novoId,
            nome = nome,
            categoria = categoria,
            descricao = descricao,
            endereco = endereco,
            telefone = telefone,
            website = website,
            imagem = imagemPadrao
        )

        val resultIntent = Intent().apply {
            putExtra("novoServico", servico)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}