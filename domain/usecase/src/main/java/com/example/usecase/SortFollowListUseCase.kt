package com.example.usecase

import com.example.model.domain.EachFollowInfo
import com.example.model.domain.FollowInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SortFollowListUseCase {
    suspend operator fun invoke(followInfo: FollowInfo, isByNew: Boolean): FollowInfo =
        withContext(Dispatchers.Main) {
            return@withContext followInfo.copy(
                followsList = if (isByNew) {
                    followInfo.followsList.sortedWith(compareByDescending<EachFollowInfo> { it.dateForSort.year }.thenByDescending { it.dateForSort.month }
                        .thenByDescending { it.dateForSort.day })
                } else {
                    followInfo.followsList.sortedWith(compareBy<EachFollowInfo> { it.dateForSort.year }.thenBy { it.dateForSort.month }
                        .thenBy { it.dateForSort.day })
                }
            )
        }
}