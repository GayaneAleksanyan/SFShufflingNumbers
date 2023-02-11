package com.example.shufflingnumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val list = Array(30) { i ->
            i
        }

        val adapter = MyAdapter(list)
        recyclerView.adapter = adapter

        fun shuffle() {
//            val adapter = (recyclerView.adapter as Adapter)
            val newList = Array(30) { i ->
                i
            }
            val r = Random(System.currentTimeMillis())

            for (i in newList.indices) {
                val newI = r.nextInt(adapter.data.size)
                val temp = newList[i]
                newList[i] = newList[newI]
                newList[newI] = temp
            }

            val oldList = adapter.data
            val numbersDiff = NumbersDiff(oldList, newList)
            val diffResult = DiffUtil.calculateDiff(numbersDiff)
            adapter.data = newList
            diffResult.dispatchUpdatesTo(adapter)
        }

        val shuffle = findViewById<Button>(R.id.shuffle)
        shuffle.setOnClickListener {
            shuffle()
        }
    }
}