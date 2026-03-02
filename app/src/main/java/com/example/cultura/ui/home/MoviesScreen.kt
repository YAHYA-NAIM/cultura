package com.example.cultura.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cultura.R
import com.example.cultura.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MoviesScreen() {

    val movies = listOf(
        Movie(1, "The Shawshank Redemption", "Two imprisoned men bond over a number of years...", "1994-09-23", "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg"),
        Movie(2, "The Godfather", "The aging patriarch of an organized crime dynasty...", "1972-03-14", "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg"),
        Movie(3, "The Dark Knight", "Batman raises the stakes in his war on crime...", "2008-07-16", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"),
        Movie(4, "Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster's wife...", "1994-10-14", "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg"),
        Movie(5, "Inception", "A thief who steals corporate secrets...", "2010-07-16", "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg"),
        Movie(6, "Forrest Gump", "The presidencies of Kennedy and Johnson...", "1994-07-06", "https://image.tmdb.org/t/p/w500/saHP97rTPS5eLmrLQEcANmKrsFl.jpg"),
        Movie(7, "The Matrix", "A computer hacker learns from mysterious rebels...", "1999-03-31", "https://image.tmdb.org/t/p/w500/xB76yMLfiR2xcoZbwDg0OjH9vkm.jpg"),
        Movie(8, "The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead the World of Men against Sauron...", "2003-12-17", "https://image.tmdb.org/t/p/w500/ldwZcKc3yxy9ZG3hytwS9t7pPVb.jpg"),
        Movie(9, "The Avengers", "Earth's mightiest heroes must come together...", "2012-05-04", "https://image.tmdb.org/t/p/w500/rkIR9mjE7XWrr35GsS9fCh9WfLO.jpg"),
        Movie(10, "Interstellar", "A team of explorers must find a new habitable planet...", "2014-11-07", "https://image.tmdb.org/t/p/w500/xCxWZ6O2gBws6X7du4kWln4t5vY.jpg")
    )



    var isLoading by remember { mutableStateOf(true) }
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }

  
    LaunchedEffect(Unit) {
        delay(2000)
        movieList = movies 
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Movies",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily(Font(R.font.montagumedium))
        )

        if (isLoading) {
            
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // Show movies after loading
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movieList) { movie ->
                    MovieGridItem(movie)
                }
            }
        }
    }
}

@Composable
fun MovieGridItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column {
            // Movie poster
            AsyncImage(
                model = movie.posterPath,
                contentDescription = "${movie.title} poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Movie title
            Text(
                text = movie.title,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )

            // Release date
            Text(
                text = "Released: ${movie.releaseDate}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MaterialTheme {
        MoviesScreen()
    }
}
