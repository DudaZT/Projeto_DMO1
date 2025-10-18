package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.adapter.ServicoAdapter
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityMainBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var servicos: List<Servico>
    private var servicoSelecionado: Servico? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupList()
        setupButtons()
        setupBackPressedHandler()
    }

    private fun loadData() {
        servicos = listOf(
            Servico(
                1,
                getString(R.string.upa_vila_xavier),
                getString(R.string.category_health),
                getString(R.string.upa_vila_xavier_description),
                getString(R.string.upa_vila_xavier_address),
                getString(R.string.upa_vila_xavier_phone),
                "https://www.araraquara.sp.gov.br/servicos/upas",
                R.drawable.upa
            ),
            Servico(
                2,
                getString(R.string.savegnago),
                getString(R.string.category_retail),
                getString(R.string.savegnago_description),
                getString(R.string.savegnago_address),
                getString(R.string.savegnago_phone),
                "https://www.savegnago.com.br/araraquara",
                R.drawable.savegnago
            ),
            Servico(
                3,
                getString(R.string.tokio_bar),
                getString(R.string.category_food),
                getString(R.string.tokio_bar_description),
                getString(R.string.tokio_bar_address),
                getString(R.string.tokio_bar_phone),
                "https://bio.site/tokiobar",
                R.drawable.tokio_bar
            ),
            Servico(
                4,
                getString(R.string.pipocopos),
                getString(R.string.category_food),
                getString(R.string.pipocopos_description),
                getString(R.string.pipocopos_address),
                getString(R.string.pipocopos_phone),
                "https://pipocopos.com.br",
                R.drawable.pipocopos
            ),
            Servico(
                5,
                getString(R.string.sesi),
                getString(R.string.category_education),
                getString(R.string.sesi_description),
                getString(R.string.sesi_address),
                getString(R.string.sesi_phone),
                "https://www.sesisp.org.br",
                R.drawable.sesi
            ),
            Servico(
                6,
                getString(R.string.pastelaria_amada),
                getString(R.string.category_food),
                getString(R.string.pastelaria_amada_description),
                getString(R.string.pastelaria_amada_address),
                getString(R.string.pastelaria_amada_phone),
                "https://pastelariadaamada.delmatchcardapio.com",
                R.drawable.pastelaria_amada
            )
        ).sortedBy { it.nome }
    }

    private fun setupList() {
        val adapter = ServicoAdapter(this, servicos)
        binding.listViewServicos.adapter = adapter

        binding.listViewServicos.setOnItemClickListener { _, _, position, _ ->
            val servico = servicos[position]
            mostrarDetalhes(servico)
        }
    }

    private fun setupButtons() {
        binding.btnVoltar.setOnClickListener {
            voltarParaLista()
        }

        binding.btnLigar.setOnClickListener {
            servicoSelecionado?.let { servico ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${servico.telefone}")
                }
                startActivity(intent)
            }
        }

        binding.btnSite.setOnClickListener {
            servicoSelecionado?.let { servico ->
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(servico.website)
                }
                startActivity(intent)
            }
        }

        binding.btnMaps.setOnClickListener {
            servicoSelecionado?.let { servico ->
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=${Uri.encode(servico.endereco)}")
                }
                startActivity(intent)
            }
        }

        binding.btnCompartilhar.setOnClickListener {
            servicoSelecionado?.let { servico ->
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,
                        "${servico.nome}\n${servico.descricao}\n${servico.telefone}\n${servico.endereco}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_service)))
            }
        }
    }

    private fun setupBackPressedHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.layoutDetalhes.visibility == View.VISIBLE) {
                    voltarParaLista()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun mostrarDetalhes(servico: Servico) {
        servicoSelecionado = servico
        binding.layoutLista.visibility = View.GONE
        binding.layoutDetalhes.visibility = View.VISIBLE

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

    private fun voltarParaLista() {
        binding.layoutDetalhes.visibility = View.GONE
        binding.layoutLista.visibility = View.VISIBLE
        servicoSelecionado = null
    }

}