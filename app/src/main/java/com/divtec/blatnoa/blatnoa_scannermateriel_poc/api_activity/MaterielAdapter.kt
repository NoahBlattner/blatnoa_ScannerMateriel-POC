package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.R
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api_activity.MaterielViewHolder
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.apidata.ApiMateriel

class MaterielAdapter(
    private val materiels: ArrayList<ApiMateriel>)
    : RecyclerView.Adapter<MaterielViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterielViewHolder {
        // Inflate a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.materiel_item_layout, parent, false)

        // Create and return a new view holder
        return MaterielViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaterielViewHolder, position: Int) {
        // Set the data of the view holder
        holder.setData(materiels[position])
    }

    override fun getItemCount(): Int {
        return materiels.size
    }

    public fun setMateriels(materiels: ArrayList<ApiMateriel>) {
        this.materiels.clear()
        this.materiels.addAll(materiels)
        notifyDataSetChanged()
    }
}