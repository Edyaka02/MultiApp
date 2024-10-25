package com.example.multiapp.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.multiapp.R
import com.example.multiapp.components.MainIconButton
import com.example.multiapp.components.TitleBar

@Composable
fun AñosPerrunos(){
    PosicionPantalla(
        titulo="Mis Años Perrunos",
        imagen= painterResource(id= R.drawable.perro_2)

    )
}
@Composable
private fun PosicionPantalla(titulo:String, imagen: Painter, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    val teclado = LocalSoftwareKeyboardController.current


    Column(
        modifier=modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var edad by remember { mutableStateOf("") }
        var resultado by remember {
            mutableStateOf("")
        }
        Image(
            painter=imagen,
            contentDescription =null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Text(
            text=titulo,
            modifier= Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Mi edad humana") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )

        )

        Row(
        ){
            Box(modifier=modifier.padding(16.dp)){
                ElevatedButton(
                    onClick = {
                        try {
                            var res=0
                            res = edad.toInt() * 7
                            resultado=res.toString()
                        }catch ( e: NumberFormatException ){
                            Toast.makeText(contexto, "Solo ingresar numeros enteros.", Toast.LENGTH_SHORT).show()
                            edad = ""
                            resultado = ""
                        }
                        teclado?.hide()

                    })
                {
                    Text("Calcular")
                }
            }

            Box(modifier=modifier.padding(16.dp)){
                ElevatedButton(
                    onClick = {
                        edad = ""
                        resultado = ""
                    })
                {
                    Text("Limpiar")
                }
            }

        }


        OutlinedTextField(
            value = resultado,
            readOnly = true,
            onValueChange = { resultado = it },
            label = { Text("Edad Perruna") }
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DogYearDetailView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Descuento") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(5, 169, 166, 255)
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        AñosPerrunos()
    }
}
