package com.example.krunalshah.info6124_lab2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    var myList = ArrayList<String?>()
    val data = ArrayList<ItemsViewModel>()
    val adapter = myAdapter(data)
    var message: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val removeLastButton: Button = findViewById(R.id.removeLastButton)
        removeLastButton.setOnClickListener {
//            if (adapter.itemCount != 0) {
                data.removeLast()
                myList.removeLast()
//            }
            adapter.notifyDataSetChanged()
        }

        val removeAllButton: Button = findViewById(R.id.removeAllButton)
        removeAllButton.setOnClickListener {
            data.clear()
            myList.clear()
            adapter.notifyDataSetChanged()
        }

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        message = intent.getStringExtra("message")

        myList = getArrayPrefs(R.string.kitchen_list.toString(), this)
        myList.add(message)

        myList.forEach {
            if (it!=null) {
                data.add(ItemsViewModel(it!!))
            }
        }
        adapter.notifyDataSetChanged()
    }

    fun setArrayPrefs(arrayName: String, array: ArrayList<String?>, mContext: Context) {
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(arrayName + "_size", array.size)

        for (i in 0 until array.size) editor.putString(arrayName + "_" + i, array[i])
        editor.apply()
    }

    fun getArrayPrefs(arrayName: String, mContext: Context): ArrayList<String?> {
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val size = prefs.getInt(arrayName + "_size", 0)
        val array: ArrayList<String?> = ArrayList(size)

        for (i in 0 until size) array.add(prefs.getString(arrayName + "_" + i, null))

        return array
    }

    override fun onStop() {
        super.onStop()
        setArrayPrefs(R.string.kitchen_list.toString(), myList, this)
    }
}