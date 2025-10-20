package com.example.guiapocket_bairrovilaxavier.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guiapocket_bairrovilaxavier.R
import com.example.guiapocket_bairrovilaxavier.adapter.ServicoAdapter
import com.example.guiapocket_bairrovilaxavier.databinding.ActivityMainBinding
import com.example.guiapocket_bairrovilaxavier.model.Servico

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var servicos: List<Servico>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
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

    private fun setupViews(){
        val adapter = ServicoAdapter(this, servicos)
        binding.listViewServicos.adapter = adapter
    }
    private fun setupListeners() {
        binding.listViewServicos.setOnItemClickListener { _, _, position, _ ->
            // Abre a nova Activity
            val intent = Intent(this, DetalheServicoActivity::class.java)
            intent.putExtra("servico", servicos[position])
            startActivity(intent)
        }
    }
}