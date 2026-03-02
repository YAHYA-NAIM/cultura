package com.example.cultura.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cultura.R
import com.example.cultura.data.model.Book
import com.example.cultura.ui.theme.CulturaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BooksScreen() {
    // Simulated book data (static list of 10 books)
    val books = listOf(
        Book(1, "To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice and moral growth in the American South.", "https://covers.openlibrary.org/b/id/8231990-L.jpg"),
        Book(2, "1984", "George Orwell", "A dystopian novel about totalitarianism, surveillance, and censorship.", "https://covers.openlibrary.org/b/id/8575111-L.jpg"),
        Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "A novel depicting the excesses of the Jazz Age and the American Dream.", "https://covers.openlibrary.org/b/id/8371513-L.jpg"),
        Book(4, "Pride and Prejudice", "Jane Austen", "A romantic novel about social manners and misunderstandings.", "https://covers.openlibrary.org/b/id/12645114-L.jpg"),
        Book(5, "The Catcher in the Rye", "J.D. Salinger", "A story of teenage angst and alienation in post-war America.", "https://covers.openlibrary.org/b/id/8231989-L.jpg"),
        Book(6, "The Hobbit", "J.R.R. Tolkien", "A fantasy novel about a hobbit's journey to reclaim treasure from a dragon.", "https://covers.openlibrary.org/b/id/8405172-L.jpg"),
        Book(7, "The Picture of Dorian Gray", "Oscar Wilde", "A novel about a man who remains young while a portrait ages, reflecting his moral corruption.", "https://covers.openlibrary.org/b/id/8258302-L.jpg"),
        Book(8, "Brave New World", "Aldous Huxley", "A dystopian novel about a technologically advanced society that suppresses individuality.", "https://covers.openlibrary.org/b/id/8304730-L.jpg"),
        Book(9, "Moby-Dick", "Herman Melville", "The epic tale of a sailor's obsession with hunting a giant white whale.", "https://covers.openlibrary.org/b/id/8264701-L.jpg"),
        Book(10, "War and Peace", "Leo Tolstoy", "A historical novel about the Napoleonic wars and the lives of Russian aristocracy.", "https://covers.openlibrary.org/b/id/8326217-L.jpg")
    )

    // State for loading simulation
    var isLoading by remember { mutableStateOf(true) }
    var bookList by remember { mutableStateOf<List<Book>>(emptyList()) }

    // Simulate a loading delay
    LaunchedEffect(Unit) {
        delay(2000) // Simulate a network delay
        bookList = books // Replace with your actual data
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Books",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily(Font(R.font.montagumedium))
        )

        if (isLoading) {
            // Show loading spinner while data is loading
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // Show books after loading
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(bookList) { book ->
                    BookItem(book)
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 6.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFFAF8F1)
    ) {
        Row(modifier = Modifier.height(160.dp)) {
            // Book cover image
            AsyncImage(
                model = book.coverImageUrl,
                contentDescription = "${book.title} cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            )

            // Book details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Book title
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Author
                    Text(
                        text = "by ${book.author}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Description preview
                Text(
                    text = book.description,
                    style = MaterialTheme.typography.caption,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun BooksScreenPreview() {
    CulturaTheme {
        BooksScreen()
    }
}
