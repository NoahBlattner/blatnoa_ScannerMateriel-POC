package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api

import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.apidata.ApiMateriel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import okhttp3.Response
import org.json.JSONArray

class DivtecApiManager(url: String) : ApiManager(url) {

    /**
     * Request the list of materials from the Divtec API
     * @param responseCallback The callback to call when the request is done
     */
    public fun requestMateriels(responseCallback: OnApiResponseCallback<ArrayList<ApiMateriel>>) {
        val apiAction = "/prets/materiels"
        run(apiAction, object : OnApiResponseCallback<Response> {
            override fun onSuccess(data: Response) {
                // Parse the response
                val materiels = ArrayList<ApiMateriel>()
                val json = data.body.string()
                val jsonArray = JSONArray(json)

                for (i in 0 until jsonArray.length()) {
                    val jsonMateriel = jsonArray.getJSONObject(i)
                    val materiel = Json.decodeFromString(ApiMateriel.serializer(), jsonMateriel.toString())
                    materiels.add(materiel)
                }

                // Call the callback
                responseCallback.onSuccess(materiels)
            }
            override fun onError(response: Response?) {
                // Call the callback
                responseCallback.onError(response)
            }
        })
    }
}

