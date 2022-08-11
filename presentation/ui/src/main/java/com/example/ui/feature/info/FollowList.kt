package com.example.ui.feature.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.model.domain.EachFollowInfo

@Composable
fun FollowList(
    followInfo: List<EachFollowInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(followInfo) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = it.toName,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = it.followedAt,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}