package com.example.ui.feature.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.model.domain.EachFollowInfo

fun LazyGridScope.FollowList(
    followInfo: List<EachFollowInfo>,
    modifier: Modifier = Modifier,
) {
    items(followInfo) {
        Card {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
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