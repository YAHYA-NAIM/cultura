package com.example.cultura.ui.home

import com.example.cultura.ui.theme.CulturaTheme
import com.example.cultura.R

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cultura.ui.theme.PrimaryLight

@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabItem("Movies", R.drawable.movie),
        TabItem("Books", R.drawable.book)
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = PrimaryLight // or background, or your custom color
            ) {
                tabs.forEachIndexed { index, tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = tab.iconResourceId),
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(tab.title) },
                        selected = selectedTab == index,
                        selectedContentColor = MaterialTheme.colors.onPrimary, // White when selected
                        unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.6f), // Lighter color when unselected
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> MoviesScreen()
                1 -> BooksScreen()
            }
        }
    }
}

data class TabItem(val title: String, val iconResourceId: Int)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CulturaTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}
