package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.controller

import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.Configuracao
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.MainActivity
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model.ConfigSqlite
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model.DAO

class ConfigController(mainActivity: MainActivity) {
    private val dao: DAO = ConfigSqlite(mainActivity)

    fun inserirConfig(config:Configuracao) = dao.criarConfig(config)
    fun editarConfig(config: Configuracao) = dao.atualizarConfig(config)
    fun buscarConfig() = dao.recuperarConfiguracao()
}

