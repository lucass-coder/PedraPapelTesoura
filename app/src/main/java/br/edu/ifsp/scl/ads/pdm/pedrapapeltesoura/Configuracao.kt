package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Configuracao(val numeroParticipantes: Int =-1): Parcelable
