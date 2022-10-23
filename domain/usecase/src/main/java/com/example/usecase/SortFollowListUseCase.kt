package com.example.usecase

import com.example.model.domain.EachFollowInfo
import com.example.model.domain.FollowInfo

class SortFollowListUseCase {
    operator fun invoke(followInfo: FollowInfo, isByNew: Boolean): FollowInfo {
        return followInfo.copy(
            followsList = if (isByNew) {
                followInfo.followsList.sortedWith(compareByDescending<EachFollowInfo> { it.followedAt.year }.thenByDescending { it.followedAt.month }
                    .thenByDescending { it.followedAt.day })
            } else {
                followInfo.followsList.sortedWith(compareBy<EachFollowInfo> { it.followedAt.year }.thenBy { it.followedAt.month }
                    .thenBy { it.followedAt.day })
            }
        )
    }
}