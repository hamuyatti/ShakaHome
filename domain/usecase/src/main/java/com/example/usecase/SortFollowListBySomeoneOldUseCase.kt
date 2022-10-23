package com.example.usecase

import com.example.model.domain.FollowInfo

class SortFollowListBySomeoneOldUseCase {
    operator fun invoke(followInfo: FollowInfo): FollowInfo {
        return followInfo.copy(
            followsList = followInfo.followsList.sortedByDescending { it.index }
        )
    }
}