package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.controller.ConfigController
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import java.sql.SQLException
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var geradorRandomico : Random

    private lateinit var settingsActivityLauncher : ActivityResultLauncher<Intent>
    private lateinit var configController: ConfigController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(activityMainBinding.root)
        geradorRandomico = Random(System.currentTimeMillis())

        // =================================================================================== //
        //                                 FAZENDO O BANCO DE DADOS                            //
        // =================================================================================== //
        configController = ConfigController(this)
        val conf : Configuracao = configController.buscarConfig()

        if(conf.numeroParticipantes == 2) {
            // Usuário escolhe pedra
            activityMainBinding.imagemPedra.setOnClickListener{
                this.opcaoSelecionada2Participantes("pedra")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("pedra", "drawable", packageName)
                )
            }
            // Usuário escolhe papel
            activityMainBinding.imagemPapel.setOnClickListener{
                this.opcaoSelecionada2Participantes("papel")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("papel", "drawable", packageName)
                )
            }
            // Usuário escolhe tesoura
            activityMainBinding.imagemTesoura.setOnClickListener{
                this.opcaoSelecionada2Participantes("tesoura")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("tesoura", "drawable", packageName)
                )
            }
        } else {
            "Jogador 2".also {
                activityMainBinding.textoJogador2.text = it
            }
            "".also {
                activityMainBinding.textResultado.text = it
            }
            activityMainBinding.imageResultadoJogador2.setImageResource(
                resources.getIdentifier("padrao", "drawable", packageName)
            )
            activityMainBinding.imageResultadoJogador1.setImageResource(
                resources.getIdentifier("padrao", "drawable", packageName)
            )
            activityMainBinding.imageResultadoHumano.setImageResource(
                resources.getIdentifier("padrao", "drawable", packageName)
            )

            // Usuário escolhe pedra
            activityMainBinding.imagemPedra.setOnClickListener{
                this.opcaoSelecionada3Parcicipantes("pedra")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("pedra", "drawable", packageName)
                )
            }
            // Usuário escolhe papel
            activityMainBinding.imagemPapel.setOnClickListener{
                this.opcaoSelecionada3Parcicipantes("papel")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("papel", "drawable", packageName)
                )
            }
            // Usuário escolhe tesoura
            activityMainBinding.imagemTesoura.setOnClickListener{
                this.opcaoSelecionada3Parcicipantes("tesoura")
                activityMainBinding.imageResultadoHumano.setImageResource(
                    resources.getIdentifier("tesoura", "drawable", packageName)
                )
            }

        }


        // =================================================================================== //
        //                      Chamada padrão quando inicia o app                             //
        // =================================================================================== //



        // =================================================================================== //
        //                 Quando o usuário escolhe a quantidade de jogadores                  //
        // =================================================================================== //
        settingsActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK) {
                if(result.data != null) {
                    val configuracao : Configuracao? = result.data?.getParcelableExtra(Intent.EXTRA_USER)
                    if (configuracao != null) {
                        configController.editarConfig(Configuracao(configuracao.numeroParticipantes))
                        if (configuracao.numeroParticipantes == 2) {
                        "".also {
                            activityMainBinding.textoJogador2.text = it
                        }
                        "".also {
                            activityMainBinding.textResultado.text = it
                        }
                        activityMainBinding.imageResultadoJogador2.setImageResource(0)
                        activityMainBinding.imageResultadoJogador1.setImageResource(
                            resources.getIdentifier("padrao", "drawable", packageName)
                        )
                        activityMainBinding.imageResultadoHumano.setImageResource(
                            resources.getIdentifier("padrao", "drawable", packageName)
                        )

                        // Usuário escolhe pedra
                        activityMainBinding.imagemPedra.setOnClickListener{
                            this.opcaoSelecionada2Participantes("pedra")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("pedra", "drawable", packageName)
                            )
                        }
                        // Usuário escolhe papel
                        activityMainBinding.imagemPapel.setOnClickListener{
                            this.opcaoSelecionada2Participantes("papel")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("papel", "drawable", packageName)
                            )
                        }
                        // Usuário escolhe tesoura
                        activityMainBinding.imagemTesoura.setOnClickListener{
                            this.opcaoSelecionada2Participantes("tesoura")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("tesoura", "drawable", packageName)
                            )
                        }
                    } else if (configuracao.numeroParticipantes == 3) {
                        "Jogador 2".also {
                            activityMainBinding.textoJogador2.text = it
                        }
                        "".also {
                            activityMainBinding.textResultado.text = it
                        }
                        activityMainBinding.imageResultadoJogador2.setImageResource(
                            resources.getIdentifier("padrao", "drawable", packageName)
                        )
                        activityMainBinding.imageResultadoJogador1.setImageResource(
                            resources.getIdentifier("padrao", "drawable", packageName)
                        )
                        activityMainBinding.imageResultadoHumano.setImageResource(
                            resources.getIdentifier("padrao", "drawable", packageName)
                        )

                        // Usuário escolhe pedra
                        activityMainBinding.imagemPedra.setOnClickListener{
                            this.opcaoSelecionada3Parcicipantes("pedra")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("pedra", "drawable", packageName)
                            )
                        }
                        // Usuário escolhe papel
                        activityMainBinding.imagemPapel.setOnClickListener{
                            this.opcaoSelecionada3Parcicipantes("papel")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("papel", "drawable", packageName)
                            )
                        }
                        // Usuário escolhe tesoura
                        activityMainBinding.imagemTesoura.setOnClickListener{
                            this.opcaoSelecionada3Parcicipantes("tesoura")
                            activityMainBinding.imageResultadoHumano.setImageResource(
                                resources.getIdentifier("tesoura", "drawable", packageName)
                            )
                        }

                    }
                    }
                }
            }
            }
    }

    // =================================================================================== //
    //                          Quando o usuário escolhe 2 participantes                   //
    // =================================================================================== //
    private fun opcaoSelecionada2Participantes(escolhaUsuario : String){

        // val textoResultado : TextView = findViewById(R.id.textResultado)

        val numero = geradorRandomico.nextInt(0..2)
        val opcoes = arrayOf("pedra", "papel", "tesoura")
        val escolhaJogador1 = opcoes[numero]
        activityMainBinding.imageResultadoJogador1.setImageResource(
            resources.getIdentifier(escolhaJogador1, "drawable", packageName)
        )

        // Jogador1 Ganhador
        if(
            (escolhaJogador1 == "pedra" && escolhaUsuario == "tesoura") ||
            (escolhaJogador1 == "papel" && escolhaUsuario == "pedra")   ||
            (escolhaJogador1 == "tesoura" && escolhaUsuario == "papel")

        ) {
            "Jogador 1 Venceu! =( ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Jogador 1 Venceu! =( ")
        }

        // Usuario ganhador
        else if (
            (escolhaUsuario == "pedra" && escolhaJogador1 == "tesoura") ||
            (escolhaUsuario == "papel" && escolhaJogador1 == "pedra")   ||
            (escolhaUsuario == "tesoura" && escolhaJogador1 == "papel")
        ) {
            "Você Venceu! =) ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Você Venceu! =) ")
        }

        // Empate
        else {
            "Empatou! =/ ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Empatou! =/ ")
        }

    }

    // =================================================================================== //
    //                          Quando o usuário escolhe 3 participantes                   //
    // =================================================================================== //
    private fun opcaoSelecionada3Parcicipantes(escolhaUsuario : String){

        // val textoResultado : TextView = findViewById(R.id.textResultado)

        val numero = geradorRandomico.nextInt(0..2)
        val opcoes = arrayOf("pedra", "papel", "tesoura")
        val escolhaJogador1 = opcoes[numero]
        activityMainBinding.imageResultadoJogador1.setImageResource(
            resources.getIdentifier(escolhaJogador1, "drawable", packageName)
        )

        val numero2 = geradorRandomico.nextInt(0..2)
        val escolhaJogador2 = opcoes[numero2]
        activityMainBinding.imageResultadoJogador2.setImageResource(
            resources.getIdentifier(escolhaJogador2, "drawable", packageName)
        )

        // Jogador1 Ganhador
        if(
            (escolhaJogador1 == "pedra" && escolhaJogador2 == "tesoura" && escolhaUsuario == "tesoura") ||
            (escolhaJogador1 == "papel" && escolhaJogador2 == "pedra" && escolhaUsuario == "pedra") ||
            (escolhaJogador1 == "tesoura" && escolhaJogador2 == "papel" && escolhaUsuario == "papel")

        ) {
            "Jogador 1 Venceu! =( ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Jogador 1 Venceu! =( ")
        }

        // Jogador2 Ganhador
        else if(
            (escolhaJogador2 == "pedra" && escolhaJogador1 == "tesoura" && escolhaUsuario == "tesoura") ||
            (escolhaJogador2 == "papel" && escolhaJogador1 == "pedra" && escolhaUsuario == "pedra") ||
            (escolhaJogador2 == "tesoura" && escolhaJogador1 == "papel" && escolhaUsuario == "papel")

        ) {
            "Jogador 2 Venceu! =( ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Jogador 2 Venceu! =( ")
        }

        // Usuario ganhador
        else if (
            (escolhaUsuario == "pedra" && escolhaJogador1 == "tesoura" && escolhaJogador2 == "tesoura") ||
            (escolhaUsuario == "papel" && escolhaJogador1 == "pedra" && escolhaJogador2 == "pedra")   ||
            (escolhaUsuario == "tesoura" && escolhaJogador1 == "papel" && escolhaJogador2 == "papel")
        ) {
            "Você Venceu! =) ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Você Venceu! =) ")
        }
        // Empate
        else {
            "Empatou! =/ ".also { activityMainBinding.textResultado.text = it }
            //textoResultado.setText("Empatou! =/ ")
        }
    }

    // =================================================================================== //
    //                          Configuração do menu e configurações                       //
    // =================================================================================== //

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if(item.itemId == R.id.settingsMi){
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityLauncher.launch(settingsIntent)
            return true
        }
        return false
    }

    // FORMA ANTIGA DE FAZER  - Forma que comecei a fazer, utilizando o onClick
//    fun selecionadoPedra(view : View){
//        //imageResultadoHumano
//        val imagemResultadoHumano : ImageView = findViewById(R.id.imageResultadoHumano)
//        imagemResultadoHumano.setImageResource(R.drawable.pedra)
//        this.opcaoSelecionada("pedra")
//    }
//    fun selecionadoPapel(view : View){
//        val imagemResultadoHumano : ImageView = findViewById(R.id.imageResultadoHumano)
//        imagemResultadoHumano.setImageResource(R.drawable.papel)
//        this.opcaoSelecionada("papel")
//    }
//    fun selecionadoTesoura(view : View){
//        val imagemResultadoHumano : ImageView = findViewById(R.id.imageResultadoHumano)
//        imagemResultadoHumano.setImageResource(R.drawable.tesoura)
//        this.opcaoSelecionada("tesoura")
//    }



}