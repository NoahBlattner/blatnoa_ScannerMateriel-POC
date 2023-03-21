package com.divtec.blatnoa.blatnoa_scannermateriel_poc

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class NfcReadActivity : AppCompatActivity() {
    private lateinit var nfcDataText: TextView
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nfc_activity)

        nfcDataText = findViewById(R.id.nfcResult)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_SHORT).show()
            finish()
        }

        onNfcRead(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)

        onNfcRead(intent!!)
    }

    /**
     * Called when nfc data is received
     */
    private fun onNfcRead(intent: Intent) {
        // Show a toast message to indicate that the NFC intent was received
        Toast.makeText(this, "NFC intent received", Toast.LENGTH_SHORT).show()

        // Get the tag from the intent and display it
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        nfcDataText.text = tag.toString()
    }

    /**
     * Open the wireless settings to enable NFC
     */
    private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }
}