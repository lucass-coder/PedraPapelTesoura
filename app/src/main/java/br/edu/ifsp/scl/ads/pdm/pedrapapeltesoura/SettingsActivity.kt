package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var activitySettingsBinding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)
        //setContentView(R.layout.activity_settings)

        activitySettingsBinding.salvarBt.setOnClickListener {
            val numeroParticipantes : Int = (activitySettingsBinding.numeroParticipantesSp.selectedView as TextView).text.toString().toInt()

            val configuracao = Configuracao(numeroParticipantes)
            val retornoIntent = Intent()
            retornoIntent.putExtra(Intent.EXTRA_USER, configuracao)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }

    }
}