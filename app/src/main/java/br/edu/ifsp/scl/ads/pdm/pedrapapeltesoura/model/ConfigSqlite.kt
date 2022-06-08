package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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


/*

class LivroSqlite(contexto: Context): LivroDAO{

    companion object {
        private val BD_LIVROS = "livros"
        private val TABELA_LIVRO = "livro"
        private val COLUNA_TITULO = "titulo"
        private val COLUNA_ISBN = "isbn"
        private val COLUNA_PRIMEIRO_AUTOR = "primeiro_autor"
        private val COLUNA_EDITORA = "editora"
        private val COLUNA_EDICAO = "edicao"
        private val COLUNA_PAGINAS = "paginas"

        */
/* Statement que será usado na primeira vez para criar a tabela. Em uma única li
        nha executada uma única vez a concatenação de String não fará diferença no desempe
        nho, além de ser mais didático *//*

        val CRIAR_TABELA_LIVRO_STMT = "CREATE TABLE IF NOT EXISTS ${TABELA_LIVRO} (" +
                "${COLUNA_TITULO} TEXT NOT NULL PRIMARY KEY, " +
                "${COLUNA_ISBN} TEXT NOT NULL, " +
                "${COLUNA_PRIMEIRO_AUTOR} TEXT NOT NULL, " +
                "${COLUNA_EDITORA} TEXT NOT NULL, " +
                "${COLUNA_EDICAO} INTEGER NOT NULL, " +
                "${COLUNA_PAGINAS} INTEGER NOT NULL );"
    }

    //referencia para banco de dados
    private val livrosBd: SQLiteDatabase
    init {
        //Criando ou abrindo o bd e conectando com o db
        livrosBd = contexto.openOrCreateDatabase(BD_LIVROS, Context.MODE_PRIVATE, null)
        //Criando a tabela
        try {
            livrosBd.execSQL(CRIAR_TABELA_LIVRO_STMT)
        }
        catch (se: SQLException){
            Log.e(contexto.getString(R.string.app_name), se.toString())
        }
    }

    override fun criarLivro(livro: Livro): Long {
        val livroCv = ContentValues()
        livroCv.put(COLUNA_TITULO, livro.titulo)
        livroCv.put(COLUNA_ISBN, livro.isbn)
        livroCv.put(COLUNA_PRIMEIRO_AUTOR, livro.primeiroAutor)
        livroCv.put(COLUNA_EDITORA, livro.editora)
        livroCv.put(COLUNA_EDICAO, livro.edicao)
        livroCv.put(COLUNA_PAGINAS, livro.paginas)

        return livrosBd.insert(TABELA_LIVRO, null,livroCv)
        //val stmt = "insert into (titulo) values ${livro.titulo}"
    }

    override fun recuperarLivro(titulo: String): Livro {
        //consulta usando um query
        val livroCursor: Cursor = livrosBd.query(
            true, //distinct
            TABELA_LIVRO, //tabela
            null, //todas as colunas
            "${COLUNA_TITULO} = ?", //where
            arrayOf(titulo), //valores do where
            null, //groupBy
            null, //having
            null, //orderBy
            null //limit
        )

        //retorno livro encontrado ou vazio
        if (livroCursor.moveToFirst()){
            with(livroCursor){
                return Livro(
                    getString(getColumnIndexOrThrow(COLUNA_TITULO)),
                    getString(getColumnIndexOrThrow(COLUNA_ISBN)),
                    getString(getColumnIndexOrThrow(COLUNA_PRIMEIRO_AUTOR)),
                    getString(getColumnIndexOrThrow(COLUNA_EDITORA)),
                    getInt(getColumnIndexOrThrow(COLUNA_EDICAO)),
                    getInt(getColumnIndexOrThrow(COLUNA_PAGINAS))
                )
            }

        }
        else{
            return Livro()
        }
    }

    override fun recuperarLivros(): MutableList<Livro> {
        val livrosList = mutableListOf<Livro>()
        val consultaQuery = "SELECT * FROM ${TABELA_LIVRO}"
        val livrosCursor = livrosBd.rawQuery(consultaQuery, null)

        while (livrosCursor.moveToNext()){
            with(livrosCursor){
                livrosList.add(Livro(
                    getString(getColumnIndexOrThrow(COLUNA_TITULO)),
                    getString(getColumnIndexOrThrow(COLUNA_ISBN)),
                    getString(getColumnIndexOrThrow(COLUNA_PRIMEIRO_AUTOR)),
                    getString(getColumnIndexOrThrow(COLUNA_EDITORA)),
                    getInt(getColumnIndexOrThrow(COLUNA_EDICAO)),
                    getInt(getColumnIndexOrThrow(COLUNA_PAGINAS))
                ))
            }
        }
        return livrosList
    }

    override fun atualizarLivro(livro: Livro): Int {
        val livroCv = ContentValues()
        livroCv.put(COLUNA_TITULO, livro.titulo)
        livroCv.put(COLUNA_ISBN, livro.isbn)
        livroCv.put(COLUNA_PRIMEIRO_AUTOR, livro.primeiroAutor)
        livroCv.put(COLUNA_EDITORA, livro.editora)
        livroCv.put(COLUNA_EDICAO, livro.edicao)
        livroCv.put(COLUNA_PAGINAS, livro.paginas)

        return livrosBd.update(TABELA_LIVRO,livroCv, "${COLUNA_TITULO} = ?", arrayOf(livro.titulo))

    }

    override fun removerLivro(titulo: String): Int {
        return livrosBd.delete(TABELA_LIVRO, "${COLUNA_TITULO} = ?", arrayOf(titulo))
    }
}*/
