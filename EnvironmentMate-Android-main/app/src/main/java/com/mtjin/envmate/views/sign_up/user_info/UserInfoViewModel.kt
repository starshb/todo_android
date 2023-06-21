package com.mtjin.envmate.views.sign_up.user_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.data.model.request.User
import com.mtjin.envmate.data.user_info.UserInfoRepository
import com.mtjin.envmate.utils.SingleLiveEvent
import com.mtjin.envmate.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val repository: UserInfoRepository) :
    BaseViewModel() {
    var businessName = MutableLiveData("")
    var businessCode = MutableLiveData("")
    var businessIndustry = MutableLiveData("")
    var businessLocation = MutableLiveData("")
    var userName = MutableLiveData("")
    var userRank = MutableLiveData("")
    var userTel = MutableLiveData("")
    var userEmail = MutableLiveData("")
    var userPw = MutableLiveData("")

    private val _completeFrom = SingleLiveEvent<Unit>()
    private val _insertUserInfoResult = SingleLiveEvent<Boolean>()

    val completeFrom: LiveData<Unit> get() = _completeFrom
    val insertUserInfoResult: LiveData<Boolean> get() = _insertUserInfoResult


    fun completeFrom() {
        _completeFrom.call()
    }

    fun insertUserInfo() {
        Log.d("AAAAAA", userTel.value!!)
        repository.insertUserInfo(
            User(
                businessName = businessName.value!!,
                businessNumber = businessCode.value!!,
                officerEmail = userEmail.value!!,
                officerName = userName.value!!,
                officerPhone = userTel.value!!,
                officerPosition = userRank.value!!,
                password = userPw.value!!,
                industry = businessIndustry.value!!,
                locationName = businessLocation.value!!
            )
        ).flatMap {
            repository.requestSignUpAccept(
                userEmail.value!!
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(onSuccess = {
                Log.d(TAG, "insertUserInfo() onSuccess -> $it")
                Log.d(TAG, "insertUserInfo() onSuccess -> " + it.message)
                _insertUserInfoResult.value = true
            }, onError = {
                Log.d(TAG, "insertUserInfo() onError -> " + it.localizedMessage)
                _insertUserInfoResult.value = false
            }).addTo(compositeDisposable)
    }


}