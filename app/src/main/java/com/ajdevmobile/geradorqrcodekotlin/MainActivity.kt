package com.ajdevmobile.geradorqrcodekotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.createBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    lateinit var edit_conteudo: EditText
    lateinit var bt_gerarQRCode: Button
    lateinit var iv_QRCode: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponentes()

        bt_gerarQRCode.setOnClickListener {

            if(TextUtils.isEmpty(edit_conteudo.text.toString())){

                edit_conteudo.error = "*"
                edit_conteudo.isFocusable = true

            }else{

                gerarQRCode( edit_conteudo.text.toString())

            }

        }

    }


    fun initComponentes(){

        edit_conteudo = findViewById(R.id.editQRCode)
        bt_gerarQRCode = findViewById(R.id.btnGerarQRCode)
        iv_QRCode = findViewById(R.id.imgQRCode)

    }

    fun gerarQRCode(conteudo: String){

            val oRCode = QRCodeWriter()

            val  bitMatrix  = oRCode.encode(conteudo, BarcodeFormat.QR_CODE, 196, 196)

            val largura = bitMatrix.width
            val altura = bitMatrix.height

            val bitmap = createBitmap(largura, altura, Bitmap.Config.RGB_565)


           for(x in 0 until largura){

               for(y in 0 until altura){

                     bitmap.setPixel(x, y, if(bitMatrix[x, y]) Color.BLACK else Color.WHITE )
               }

           }

            iv_QRCode.setImageBitmap( bitmap )

    }
}