package com.divtec.blatnoa.blatnoa_scannermateriel_poc.api_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.R
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.DivtecApiManager
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.OnApiResponseCallback
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api.apidata.ApiMateriel
import com.divtec.blatnoa.blatnoa_scannermateriel_poc.api_activity.MaterielAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.Response

class ApiActivity : AppCompatActivity() {
    private val DEFAULT_API_URL = "http://localhost:8080"

    private val apiManager = DivtecApiManager(DEFAULT_API_URL)

    private lateinit var materialAdapter: MaterielAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        // Load the menu
        val actionBar : Toolbar? = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(actionBar)

        // Load the list of materials
        reloadList()
    }

    /**
     * Reload the list of materials
     */
    private fun reloadList() {
        // Request the list of materials
        apiManager.requestMateriels(object : OnApiResponseCallback<ArrayList<ApiMateriel>> {
            override fun onSuccess(data: ArrayList<ApiMateriel>) {
                // Set the list of materials
                materialAdapter.setMateriels(data)
            }
            override fun onError(response: Response?) {
                runOnUiThread(Runnable { // On the UI thread
                    // Show an error message
                    Toast.makeText(this@ApiActivity,
                        "Error while loading the list of materials" + response?.code,
                        Toast.LENGTH_SHORT)
                        .show()

                    modifyApiUrl()
                })
            }
        })
    }

    /**
     * Modify the API URL with a dialog
     */
    private fun modifyApiUrl() {
        val urlEdit = EditText(this)
        urlEdit.setText(apiManager.getUri())

        // Text edit dialog
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle("API URL")
        builder.setMessage("Enter the API URL")
        builder.setView(urlEdit)
        builder.setPositiveButton("OK") { _, _ ->
            // Set the new URL
            apiManager.setUri(urlEdit.text.toString())
            // Reload the list of materials
            reloadList()
        }
        builder.setNegativeButton("Cancel") { _, _ -> }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.set_api_url -> {
                modifyApiUrl()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}