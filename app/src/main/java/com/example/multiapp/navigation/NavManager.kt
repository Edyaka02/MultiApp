package com.example.multiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.multiapp.views.HomeView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multiapp.viewModels.LoteriaViewModel
import com.example.multiapp.views.DescuentoDetailView
import com.example.multiapp.views.DogYearDetailView
import com.example.multiapp.views.LoteriaDetailView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    val viewModel : LoteriaViewModel = viewModel()
    NavHost(navController = navController, startDestination = "Home"  ){
        composable("Home"){
            HomeView(navController)
        }

        composable("Loteria"){
            LoteriaDetailView(navController, viewModel)
        }

        composable("Descuento"){
            DescuentoDetailView(navController)
        }

        composable("DogYear"){
            DogYearDetailView(navController)
        }
    }
}