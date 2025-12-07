package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guiapocket_bairrovilaxavier.adapter.ServicoAdapter
import com.example.guiapocket_bairrovilaxavier.data.database.AppDatabase
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityMainBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ServicoAdapter
    private lateinit var database: AppDatabase
    private val servicos = mutableListOf<Servico>() // Lista em memória para filtro

    // Launcher para DetalheServicoActivity
    private val detalheLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Se voltou da tela de detalhes com resultado OK (após exclusão ou edição),
        // recarrega os dados
        if (result.resultCode == RESULT_OK) {
            carregarDados()
        }
    }

    // Launcher para CadastroServicoActivity
    private val cadastroLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Se voltou da tela de cadastro com resultado OK, recarrega os dados
        if (result.resultCode == RESULT_OK) {
            carregarDados()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o banco de dados
        database = AppDatabase.getInstance(this)

        setupViews()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        // Carrega dados do banco
        carregarDados()
    }

    private fun setupViews() {
        binding.recyclerViewServicos.layoutManager = LinearLayoutManager(this)

        adapter = ServicoAdapter(emptyList()) { servico ->
            val intent = Intent(this, DetalheServicoActivity::class.java).apply {
                putExtra("servico", servico)
            }
            detalheLauncher.launch(intent)
        }

        binding.recyclerViewServicos.adapter = adapter

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(this@MainActivity, android.R.color.transparent) ?: return@apply)
        }
        binding.recyclerViewServicos.addItemDecoration(divider)
    }

    private fun setupListeners() {
        binding.btnAdicionarServico.setOnClickListener {
            val intent = Intent(this, CadastroServicoActivity::class.java)
            cadastroLauncher.launch(intent)
        }

        // Listener para o campo de filtro
        binding.edtFiltro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                aplicarFiltro(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun carregarDados() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                database.servicoDao().listarTodos().collectLatest { servicosDoBanco ->
                    // Atualiza a lista em memória
                    servicos.clear()
                    servicos.addAll(servicosDoBanco)

                    withContext(Dispatchers.Main) {
                        adapter.updateLista(servicosDoBanco)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun aplicarFiltro(filtro: String) {
        if (filtro.isEmpty()) {
            adapter.updateLista(servicos)
        } else {
            val filtroLower = filtro.lowercase()
            val filtrados = servicos.filter {
                it.nome.lowercase().contains(filtroLower) ||
                        it.categoria.lowercase().contains(filtroLower)
            }
            adapter.updateLista(filtrados)
        }
    }
}