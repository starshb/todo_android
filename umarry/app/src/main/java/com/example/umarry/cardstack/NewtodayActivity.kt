package com.example.umarry.cardstack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.umarry.databinding.ActivityMainBinding
import com.example.umarry.databinding.ActivityNewtodayBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class NewtodayActivity:AppCompatActivity() {
    private lateinit var binding: ActivityNewtodayBinding
    lateinit var newtodayAdapter: NewtodayAdapter
    lateinit var manager: CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewtodayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardStackView = binding.cardstackview

        manager = CardStackLayoutManager(baseContext, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }

        })

        val testList = mutableListOf<String>()
        testList.add("a")
        testList.add("b")
        testList.add("c")

        newtodayAdapter = NewtodayAdapter(baseContext, testList)
        cardStackView.layoutManager = manager
        cardStackView.adapter = newtodayAdapter
    }
}