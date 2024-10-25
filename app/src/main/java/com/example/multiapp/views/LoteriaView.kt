package com.example.multiapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.multiapp.components.MainButton
import com.example.multiapp.components.MainIconButton
import com.example.multiapp.components.TitleBar
import com.example.multiapp.viewModels.LoteriaViewModel

@Composable
fun LoteriaView(viewModels: LoteriaViewModel){
    val lottonNumbers = viewModels.lotoNumbers.value

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (lottonNumbers.isEmpty()){
            Text(text = "Loteria",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            LotteryNumbers(lottonNumbers)
        }
        Button(onClick = { viewModels.generateLotoNumbers() },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(5, 169, 166, 255))
            ) {
            Text(text = "Generar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun LotteryNumbers(lottonNumbers: List<Int>){
    LazyRow (
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ){
        items(lottonNumbers) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .background(Color.Red, CircleShape)
            ){
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoteriaDetailView(navController: NavController, viewModel : LoteriaViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Loteria") },
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
        LoteriaView(viewModels = viewModel)
    }
}