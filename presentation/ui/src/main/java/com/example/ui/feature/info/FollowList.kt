package com.example.ui.feature.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.model.response.EachFollowInfo

@Composable
fun FollowList(
    followInfo: List<EachFollowInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        items(followInfo) {
            Column {
                Text(
                    text = it.toName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = it.followedAt,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}