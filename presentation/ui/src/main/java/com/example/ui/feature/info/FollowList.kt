package com.example.ui.feature.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.model.domain.EachFollowInfo

fun LazyListScope.FollowList(
    followInfo: List<EachFollowInfo>,
    modifier: Modifier = Modifier,
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