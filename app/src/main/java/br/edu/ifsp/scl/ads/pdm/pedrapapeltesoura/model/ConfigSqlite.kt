package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.Configuracao
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.R
import java.sql.SQLException

class ConfigSqlite(contexto:Context): DAO {

    companion object{
        private val BD_CONF = "jogo"
        private val TABELA_CONF = "configuracoes"
        private val COLUNA_NUM_JOG = "jogadores"


        val CRIAR_TABELA_CONFIG_STMT = "CREATE TABLE IF NOT EXISTS ${TABELA_CONF} (" +
                "${COLUNA_NUM_JOG} INTEGER NOT NULL );"
    }

    private val jogoDb: SQLiteDatabase
    init {
        //Criando ou abrindo o bd e conectando com o db
        jogoDb = contexto.openOrCreateDatabase(BD_CONF, Context.MODE_PRIVATE, null)
        //Criando a tabela
        try {
            jogoDb.execSQL(CRIAR_TABELA_CONFIG_STMT)
            if(recuperarConfiguracao().numeroParticipantes == -1){
                criarConfig(Configuracao(2))
            }
        }
        catch (se: SQLException){
            Log.e(contexto.getString(R.string.app_name), se.toString())
        }
    }


    override fun criarConfig(conf: Configuracao): Long {
        val confCv = ContentValues()

        confCv.put(COLUNA_NUM_JOG, conf.numeroParticipantes)

        return jogoDb.insert(TABELA_CONF, null,confCv)
    }


    override fun atualizarConfig(config: Configuracao): Int {
        val confCv = ContentValues()
        confCv.put(COLUNA_NUM_JOG, config.numeroParticipantes)
        return jogoDb.update(TABELA_CONF,confCv, "$COLUNA_NUM_JOG", null)

    }


    override fun recuperarConfiguracao(): Configuracao {
        val consultaQuery = "SELECT * FROM ${TABELA_CONF}"
        val confCursor = jogoDb.rawQuery(consultaQuery, null)
        var conf = Configuracao()

        if (confCursor.moveToFirst()){
            with(confCursor){
                conf = Configuracao(
                    getInt(getColumnIndexOrThrow(COLUNA_NUM_JOG))
                )
            }
        }
        println("O valor recuperado do Config é: $conf")
        return conf
    }
}
