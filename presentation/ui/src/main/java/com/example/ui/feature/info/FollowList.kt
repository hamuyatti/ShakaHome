package com.example.ui.feature.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.model.domain.EachFollowInfo
import com.google.accompanist.flowlayout.FlowRow

fun LazyListScope.FollowList(
    followInfo: List<EachFollowInfo>,
    modifier: Modifier = Modifier,
    screenWidth: Int
) {
    // sticky headerを使用するために格子状のUIをlazyGridを使わないでflowRow で実現している。
    val halfWidth = (screenWidth / 2).dp
    item {
        FlowRow(modifier = modifier) {
            followInfo.forEach {
                Card(modifier = Modifier
                    .width(halfWidth)
                    .padding(8.dp)) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.toName,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = it.followedAt.toString(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}