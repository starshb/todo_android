package com.mtjin.envmate.data.chart

import com.mtjin.envmate.api.MainApiInterface
import com.mtjin.envmate.data.model.response.EnvRes
import com.mtjin.envmate.data.model.response.IndustryEnergyRes
import io.reactivex.Single
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(private val mainApiInterface: MainApiInterface) :
    ChartRepository {
    override fun requestCompareRegion(): Single<EnvRes> {
        return mainApiInterface.requestCompareRegion()
    }

    override fun requestCompareSameRegion(usage: Int): Single<EnvRes> {
        return mainApiInterface.requestCompareSameRegion(usage)
    }

    override fun requestCompareIndustryAllEnv(): Single<EnvRes> {
        return mainApiInterface.requestCompareIndustryAllEnv()
    }

    override fun requestCompareIndustrySameAll(usage: Int): Single<EnvRes> {
        return mainApiInterface.requestCompareIndustrySameAll(usage)
    }

    override fun requestDetailIndustryEnergy(
        gas: Int,
        other: Int,
        oil: Int,
        coal: Int,
        thermal: Int,
        electric: Int
    ): Single<IndustryEnergyRes> {
        return mainApiInterface.requestDetailIndustryEnergy(
            gas,
            other,
            oil,
            coal,
            thermal,
            electric
        )
    }
}