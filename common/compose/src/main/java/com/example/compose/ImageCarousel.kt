package com.example.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.uimodel.CarouselModel

@Composable
fun ImageCarousel(
    info: List<CarouselModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(items = info, key = { info -> info.description }) { info ->
            Column {
                Text(
                    text = info.description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                AsyncImage(
                    model = info.imageUrl,
                    contentDescription = info.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

            }
        }
    }
}