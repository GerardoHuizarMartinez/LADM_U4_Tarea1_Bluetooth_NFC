package com.example.ladm_u4_tarea1_bluetooth_nfc

import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_n_f_c.*

class NFC : AppCompatActivity() {

    private var nfcAdapter : NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n_f_c)

        setTitle("Opciones NFC")

        btnNFC.setOnClickListener {
            if(nfcAdapter==null){
                state.setText("False")
                support.setText("No Compatible")
                Toast.makeText(this,"Dispositivo no compatible con NFC", Toast.LENGTH_LONG).show()
            }else{
                state.setText("Activado")
                support.setText("Compatible")
            }
        }

        btnNFCOff.setOnClickListener {
            if(nfcAdapter == null){
                Toast.makeText(this,"No se puede apagar el NFC ya que el Dispositivo no compatible", Toast.LENGTH_LONG).show()
            }
        }

        backBlueetooth.setOnClickListener {
            finish()
        }

    }//on Create
}
