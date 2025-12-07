package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.data.database.AppDatabase
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityDetalheServicoBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalheServicoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheServicoBinding
    private lateinit var servico: Servico
    private lateinit var database: AppDatabase

    private val edicaoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Se a edição foi bem sucedida, fecha esta tela e volta para MainActivity
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o banco de dados
        database = AppDatabase.getInstance(this)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        servico = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("servico", Servico::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("servico") as Servico
        }
    }

    private fun setupViews() {
        binding.imgDetalheFoto.setImageURI(servico.imagem)
        binding.tvDetalheNome.text = servico.nome
        binding.tvDetalheCategoria.text = servico.categoria
        binding.tvDetalheDescricao.text = servico.descricao

        binding.tvDetalheEndereco.text = "${getString(R.string.address)}: ${servico.endereco}"
        binding.tvDetalheTelefone.text = "${getString(R.string.phone)}: ${servico.telefone}"
        binding.tvDetalheWebsite.text = "${getString(R.string.website)}: ${servico.website}"

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
            intent.data = Uri.parse("tel:${servico.telefone}")
            startActivity(intent)
        }

        binding.btnSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(servico.website)
            startActivity(intent)
        }

        binding.btnMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:0,0?q=${Uri.encode(servico.endereco)}")
            startActivity(intent)
        }

        binding.btnCompartilhar.setOnClickListener {
            val shareText = "${servico.nome}\n${servico.descricao}\n${servico.telefone}\n${servico.endereco}"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(intent, getString(R.string.share_service)))
        }

        // Botão para editar serviço
        binding.btnEditar.setOnClickListener {
            val intent = Intent(this, CadastroServicoActivity::class.java).apply {
                putExtra("servicoId", servico.id)
            }
            edicaoLauncher.launch(intent)
        }

        // Botão para excluir serviço
        binding.btnExcluir.setOnClickListener {
            mostrarDialogoConfirmacaoExclusao()
        }
    }

    private fun mostrarDialogoConfirmacaoExclusao() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirm_delete_title))
            .setMessage(getString(R.string.confirm_delete_message, servico.nome))
            .setPositiveButton(getString(R.string.confirm_delete_positive)) { dialog, which ->
                excluirServico()
            }
            .setNegativeButton(getString(R.string.confirm_delete_negative), null)
            .show()
    }

    private fun excluirServico() {
        lifecycleScope.launch(Dispatchers.IO) {
            database.servicoDao().deletar(servico)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@DetalheServicoActivity,
                    getString(R.string.success_service_deleted),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}