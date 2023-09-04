package com.example.laboratorio6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio6.Loginfun
import com.example.laboratorio6.screens.gallery.Galleryfun

@Composable
fun Laboratorio6Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Laboratorio6Screens.LoginScreen.name
    ) {
        composable(Laboratorio6Screens.LoginScreen.name){
            Loginfun(navController = navController)
        }
        composable(Laboratorio6Screens.GalleryScreen.name){
            Galleryfun(navController = navController)
        }
    }
}