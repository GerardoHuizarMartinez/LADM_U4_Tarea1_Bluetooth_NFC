package com.example.ladm_u4_tarea1_bluetooth_nfc

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //bluetooth Adapter
    lateinit var bluAdapt : BluetoothAdapter

    //Permiso bluetooth
    private val permissionBT:Int = 1
    val permissionVisi = 2

    //Mostrar la informacion de los dispositivos
    var dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Opciones Bluetooth")


        //Inicializamos el adaptador bluetooth
        bluAdapt= BluetoothAdapter.getDefaultAdapter()

        //Verificamos si dispositivo cuenta con bluetooth
        if(bluAdapt==null){
            Toast.makeText(this,"Dispositivo no es compatible con Bluetooth", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Tu Dispositivo si compatible con Bluetooth", Toast.LENGTH_LONG).show()
        }//End else



        if(bluAdapt.isEnabled){
            imageView.setImageResource(R.drawable.ic_bluetooth_on)
        }else{
            imageView.setImageResource(R.drawable.ic_bluetooth_off)
        }

        //Encender el bluetooth
        btnBlueOn.setOnClickListener {
            if(bluAdapt.isEnabled){
                Toast.makeText(this,"El bluetooth ya se escuentra encendido", Toast.LENGTH_LONG).show()
            }else{
                val requePer = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(requePer,permissionBT)
            }
        }

        //Apagar Bluetooth
        btnBlueOff.setOnClickListener {
            if(!bluAdapt.isEnabled){
                Toast.makeText(this,"El Bluetooth ya se encuentra apagado apagado", Toast.LENGTH_LONG).show()
            }else{
                //encender cuano este apagado
                bluAdapt.disable()
                imageView.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this,"Bluetooth apagado", Toast.LENGTH_LONG).show()
            }
        }

        //Activar Visibilidad
        btnBlueVisibility.setOnClickListener {
            if(!bluAdapt.isDiscovering){
                Toast.makeText(this,"Haciendo visible tu dispositivo", Toast.LENGTH_LONG).show()
                val visible = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(visible,permissionVisi)
            }else{

            }
        }


        //Obtener los dispositivos que se han emparejado l telefono
        btnBlueGetDevices.setOnClickListener {
            if(bluAdapt.isEnabled){
                val devices = bluAdapt.bondedDevices
                dataList = ArrayList<String>()

                for(device in devices){

                    val nameDev = device.name
                    val addressDev = device.address
                    val otra = device.bondState

                    var devi ="Nombre: " + nameDev +"\n" + "Direccion: " + addressDev +"\n" + "Estado: " + otra

                    dataList.add(devi)

                }
            }else{
                Toast.makeText(this,"Por favor encienda su bluetooth", Toast.LENGTH_LONG).show()
            }

            //Con esto recupera la data en la nube que ese extrajo anteriormente y la muestra en el listView
            var adaptador = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,dataList)
            list_Devices.adapter=adaptador
        }


        chanceWind.setOnClickListener {
            var nfc = Intent(this,NFC::class.java)

            startActivity(nfc)
        }


    }//End on Create

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){

            permissionBT ->  if(resultCode  == Activity.RESULT_OK){
                imageView.setImageResource(R.drawable.ic_bluetooth_on)
                Toast.makeText(this,"El bluetooth esta encendido", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No fue posible encender el bluetooth", Toast.LENGTH_LONG).show()
            }

        }//End on when

        super.onActivityResult(requestCode, resultCode, data)
    }//End on Activity Result


}//End on class Bluetooth
