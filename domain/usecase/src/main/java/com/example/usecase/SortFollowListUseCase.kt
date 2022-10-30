package com.example.usecase

import com.example.entity.EachFollowInfo
import com.example.entity.FollowInfo

class SortFollowListUseCase {
    operator fun invoke(followInfo: FollowInfo, isByNew: Boolean): FollowInfo {
        return followInfo.copy(
            followsList = if (isByNew) {
                followInfo.followsList. sortedWith(compareByDescending<EachFollowInfo> { it.dateForSort.year }.thenByDescending { it.dateForSort.month }
                    .thenByDescending { it.dateForSort.day })
            } else {
                followInfo.followsList.sortedWith(compareBy<EachFollowInfo> { it.dateForSort.year }.thenBy { it.dateForSort.month }
                    .thenBy { it.dateForSort.day })
            }
        )
    }
}