package com.itera.pam.p5.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Handson1ScreenSolusi() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Solusi Home Screen")
                Button(onClick = { navController.navigate("detail") }) {
                    Text("Buka Detail")
                }
            }
        }
        composable("detail") {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Solusi Detail Screen")
                Button(onClick = { navController.popBackStack() }) {
                    Text("< Kembali")
                }
            }
        }
    }
}

private data class ItemSolusi(val id: Int, val nama: String)

private val daftarItemSolusi = listOf(
    ItemSolusi(1, "Buku Kotlin"),
    ItemSolusi(2, "Laptop"),
    ItemSolusi(3, "Headphone"),
)

@Composable
fun Handson2ScreenSolusi() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Daftar Item (Solusi)")
                daftarItemSolusi.forEach { item ->
                    Button(onClick = { navController.navigate("detail/${item.id}") }) {
                        Text(item.nama)
                    }
                }
            }
        }
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            val item = daftarItemSolusi.find { it.id == itemId }
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Detail Item (Solusi)")
                Text("ID: $itemId")
                Text("Nama: ${item?.nama ?: "(tidak ditemukan)"}")
                Button(onClick = { navController.popBackStack() }) {
                    Text("< Kembali")
                }
            }
        }
    }
}

private data class TabSolusi(val route: String, val label: String)

private val tabsSolusi = listOf(
    TabSolusi("home", "Home"),
    TabSolusi("search", "Search"),
    TabSolusi("profil", "Profil"),
)

@Composable
fun Handson3ScreenSolusi() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                tabsSolusi.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { Text("Halaman Home (Solusi)") }
            composable("search") { Text("Halaman Search (Solusi)") }
            composable("profil") { Text("Halaman Profil (Solusi)") }
        }
    }
}
