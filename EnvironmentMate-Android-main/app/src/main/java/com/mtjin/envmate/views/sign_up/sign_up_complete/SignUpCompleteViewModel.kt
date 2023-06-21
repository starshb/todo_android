package com.mtjin.envmate.views.sign_up.sign_up_complete

import androidx.lifecycle.LiveData
import com.mtjin.envmate.base.BaseViewModel
import com.mtjin.envmate.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpCompleteViewModel @Inject constructor() : BaseViewModel() {

    private val _goLogin = SingleLiveEvent<Unit>()

    val goLogin: LiveData<Unit> get() = _goLogin

    fun goLogin() {
        _goLogin.call()
    }
}