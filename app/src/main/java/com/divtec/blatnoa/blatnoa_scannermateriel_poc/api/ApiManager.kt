package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

open class ApiManager(
    private var url: String)
{
    private val client = OkHttpClient()

    /**
     * Run a request to the API
     * @param action The action to run
     */
    public fun run(action: String, onApiResponseCallback: OnApiResponseCallback<Response>? = null) {
        // Build the request
        val request = Request.Builder()
            .url(url+action)
            .build()

        // Run the request
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) { // On error
                e.printStackTrace()

                // Call the error callback
                onApiResponseCallback?.onError()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) { // On success
                response.use {
                    if (!response.isSuccessful) throw java.io.IOException("Unexpected code $response")

                    // Call the success callback
                    onApiResponseCallback?.onSuccess(response)
                }
            }
        })
    }

    /**
     * Get the url of the API
     */
    public fun getUri(): String {
        return url
    }

    /**
     * Set the url of the API
     */
    public fun setUri(url: String) {
        this.url = url
    }

}