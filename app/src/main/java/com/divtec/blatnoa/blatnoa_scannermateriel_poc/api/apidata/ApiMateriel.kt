package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.apidata

@kotlinx.serialization.Serializable
data class ApiMateriel(
    val id: Int,
    val categorie: String,
    val nom: String,
    val type: String,
    val marque: String,
    val modele: String,
    val n_serie: String,
)
