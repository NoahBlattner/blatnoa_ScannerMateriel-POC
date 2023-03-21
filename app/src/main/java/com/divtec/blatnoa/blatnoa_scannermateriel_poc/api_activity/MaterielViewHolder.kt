package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api_activity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.R
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.apidata.ApiMateriel

class MaterielViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val txt_id = itemView.findViewById<TextView>(R.id.txt_mat_id)
    private val txt_categorie = itemView.findViewById<TextView>(R.id.txt_mat_cat)
    private val txt_nom = itemView.findViewById<TextView>(R.id.txt_mat_nom)
    private val txt_type = itemView.findViewById<TextView>(R.id.txt_mat_type)
    private val txt_marque = itemView.findViewById<TextView>(R.id.txt_mat_marque)
    private val txt_modele = itemView.findViewById<TextView>(R.id.txt_mat_mod)
    private val txt_n_serie = itemView.findViewById<TextView>(R.id.txt_mat_ns)

    /**
     * Set the data of the view holder
     * @param materiel the materiel to display
     */
    fun setData(materiel: ApiMateriel) {
        txt_id.text = materiel.id.toString()
        txt_categorie.text = materiel.categorie
        txt_nom.text = materiel.nom
        txt_type.text = materiel.type
        txt_marque.text = materiel.marque
        txt_modele.text = materiel.modele
        txt_n_serie.text = materiel.n_serie
    }
}