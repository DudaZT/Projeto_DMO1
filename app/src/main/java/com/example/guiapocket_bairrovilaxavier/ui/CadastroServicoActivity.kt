package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.data.database.AppDatabase
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityCadastroServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroServicoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroServicoBinding
    private var uriImagemSelecionada: String = ""
    private lateinit var database: AppDatabase

    // Para edição: armazena o ID do serviço que está sendo editado
    private var servicoIdParaEditar: Int = -1

    // Launcher para abrir a galeria
    private val launcherGaleria = registerForActivityResult(
        ActivityResultContracts.OpenDocument() // Abre a galeria
    ) { uri ->
        uri?.let {
            // Permissão para ler a imagem
            contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            uriImagemSelecionada = it.toString()
            binding.imgFotoCadastro.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o banco de dados
        database = AppDatabase.getInstance(this)

        // Verifica se está editando um serviço existente
        servicoIdParaEditar = intent.getIntExtra("servicoId", -1)

        if (servicoIdParaEditar != -1) {
            // Modo edição: carrega os dados do serviço
            carregarServicoParaEdicao(servicoIdParaEditar)
            binding.btnSalvar.text = getString(R.string.update)
        } else {
            // Modo cadastro: inicializa com imagem padrão
            uriImagemSelecionada = "android.resource://${packageName}/${R.drawable.pipocopos}"
            binding.imgFotoCadastro.setImageResource(R.drawable.pipocopos)
        }

        setupListeners()
    }

    private fun carregarServicoParaEdicao(servicoId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val servico = database.servicoDao().buscarPorId(servicoId)
            servico?.let {
                withContext(Dispatchers.Main) {
                    // Preenche os campos com os dados do serviço
                    uriImagemSelecionada = it.imagemUri
                    binding.imgFotoCadastro.setImageURI(it.imagem)
                    binding.etNome.setText(it.nome)
                    binding.etCategoria.setText(it.categoria)
                    binding.etDescricao.setText(it.descricao)
                    binding.etEndereco.setText(it.endereco)
                    binding.etTelefone.setText(it.telefone)
                    binding.etWebsite.setText(it.website)
                }
            }
        }
    }

    private fun setupListeners() {
        binding.imgFotoCadastro.setOnClickListener {
            launcherGaleria.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener {
            if (validarCampos()) {
                if (servicoIdParaEditar != -1) {
                    atualizarServico()
                } else {
                    salvarServico()
                }
            }
        }

        binding.btnCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        val nome = binding.etNome.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()

        var isValid = true

        if (nome.isEmpty()) {
            binding.etNome.error = getString(R.string.error_name_required)
            isValid = false
        }

        if (categoria.isEmpty()) {
            binding.etCategoria.error = getString(R.string.error_category_required)
            isValid = false
        }

        return isValid
    }

    private fun salvarServico() {
        val nome = binding.etNome.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val descricao = binding.etDescricao.text.toString().trim()
        val endereco = binding.etEndereco.text.toString().trim()
        val telefone = binding.etTelefone.text.toString().trim()
        val website = binding.etWebsite.text.toString().trim()

        val servico = Servico(
            nome = nome,
            categoria = categoria,
            descricao = descricao,
            endereco = endereco,
            telefone = telefone,
            website = website,
            imagemUri = uriImagemSelecionada
        )

        lifecycleScope.launch(Dispatchers.IO) {
            database.servicoDao().inserir(servico)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@CadastroServicoActivity,
                    getString(R.string.success_service_saved),
                    Toast.LENGTH_SHORT
                ).show()
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun atualizarServico() {
        val nome = binding.etNome.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val descricao = binding.etDescricao.text.toString().trim()
        val endereco = binding.etEndereco.text.toString().trim()
        val telefone = binding.etTelefone.text.toString().trim()
        val website = binding.etWebsite.text.toString().trim()

        val servico = Servico(
            id = servicoIdParaEditar,
            nome = nome,
            categoria = categoria,
            descricao = descricao,
            endereco = endereco,
            telefone = telefone,
            website = website,
            imagemUri = uriImagemSelecionada
        )

        lifecycleScope.launch(Dispatchers.IO) {
            database.servicoDao().atualizar(servico)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@CadastroServicoActivity,
                    getString(R.string.success_service_updated),
                    Toast.LENGTH_SHORT
                ).show()
                // Definir resultado como OK para indicar edição bem sucedida
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}