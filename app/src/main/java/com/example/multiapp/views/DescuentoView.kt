package com.example.multiapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.multiapp.components.MainIconButton
import com.example.multiapp.components.TitleBar
import com.example.multiapp.viewModels.LoteriaViewModel

@Composable
fun Vista(){
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }

    var total by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = texto1,
                onValueChange = {texto1 = it},
                label = { Text("Precio") }
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = texto2,
                onValueChange = {texto2 = it},
                label = { Text("% Descuento") }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ElevatedButton(onClick = {
                descuento = Descuento(texto1, texto2);
                total = Total(texto1, descuento) }) {
                Text("Calcular")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ElevatedButton(onClick = {
                descuento = ""
                total = ""
                texto2 = ""
                texto1 = ""
            } ) {
                Text("Limpiar")
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = descuento,
                readOnly = true,
                onValueChange = {descuento = it},
                label = { Text("% Descuento") }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                value = total,
                readOnly = true,
                onValueChange = {total = it},
                label = { Text("Total") }
            )
        }


    }
}

fun Descuento( precio: String, desc : String ):String{

    var descuento = (precio.toDouble() * desc.toDouble()) / 100

    return descuento.toString()
}

fun Total( precio : String, desc: String ):String{
    var total = ( precio.toDouble() - desc.toDouble() )

    return total.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DescuentoDetailView(navController: NavController) {
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
        Vista()
    }
}