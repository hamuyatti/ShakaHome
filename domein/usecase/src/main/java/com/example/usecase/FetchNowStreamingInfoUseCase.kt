package com.example.usecase

import com.example.irepository.NowStreamingInfoRepository
import com.example.model.domain.NowStreamingInfo
import com.example.model.response.asDomainModel

class FetchNowStreamingInfoUseCase(
    private val repository: NowStreamingInfoRepository
) {
    suspend operator fun invoke(): NowStreamingInfo {
        return repository.fetchNowStreamingInfo().asDomainModel()
    }
}