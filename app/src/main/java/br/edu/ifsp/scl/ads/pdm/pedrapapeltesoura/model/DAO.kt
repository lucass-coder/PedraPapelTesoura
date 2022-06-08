package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.Configuracao

interface DAO {
    fun criarConfig(config: Configuracao):Long
    fun atualizarConfig(config: Configuracao):Int
    fun recuperarConfiguracao(): Configuracao


}
