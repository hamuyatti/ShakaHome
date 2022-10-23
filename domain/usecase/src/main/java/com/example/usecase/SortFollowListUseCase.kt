package com.example.usecase

import com.example.model.domain.FollowInfo

class SortFollowListUseCase {
    operator fun invoke(followInfo: FollowInfo, isByNew: Boolean): FollowInfo {
        return followInfo.copy(
            followsList = if(isByNew){
                followInfo.followsList.sortedBy { it.index }
            }else{
                followInfo.followsList.sortedByDescending { it.index }
            }
        )
    }
}