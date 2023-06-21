package com.mtjin.envmate.views.photo_zoom

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseFragment
import com.mtjin.envmate.databinding.FragmentPhotoZoomBinding


class PhotoZoomFragment : BaseFragment<FragmentPhotoZoomBinding>(R.layout.fragment_photo_zoom) {
    private val args: PhotoZoomFragmentArgs by navArgs()
    override fun init() {
        initPhotoView()
    }

    private fun initPhotoView() {
        Glide.with(this@PhotoZoomFragment).load(args.imageUrl).thumbnail(0.1f)
            .into(binding.photoZoomIvImage)
    }

}