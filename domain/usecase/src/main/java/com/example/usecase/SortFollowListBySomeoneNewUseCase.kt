package com.example.usecase

import com.example.model.domain.FollowInfo

class SortFollowListBySomeoneNewUseCase {
    operator fun invoke(followInfo: FollowInfo): FollowInfo {
        return followInfo.copy(
            followsList = followInfo.followsList.sortedBy { it.index }
        )
    }
}