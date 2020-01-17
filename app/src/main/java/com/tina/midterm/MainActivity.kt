package com.tina.midterm

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.tina.midterm.dataSource.Data
import com.tina.midterm.dataSource.PublishInformation
import com.tina.midterm.viewModel.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addData()

        readFirestore()

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = MainAdapter(viewModel)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        recyclerView.adapter = adapter

        val list = listOf(Data(listOf()), Data(listOf()))

        adapter.submitList(list)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    fun readFirestore(){

        val docRef = database.collection("articles").document("2DVIzkh0vmyyQbpZLYdm")
        docRef.get().addOnCompleteListener{
            if (it.isSuccessful()) {

                val title = findViewById<TextView>(R.id.title_text)

                val tag = findViewById<TextView>(R.id.tag_text)

                val content = findViewById<TextView>(R.id.content_text)

                val author = findViewById<TextView>(R.id.author_text)

                val createTime = findViewById<TextView>(R.id.created_time_text)

                val document = it.getResult()

                val data = document?.data



                if (document!!.exists()) {
                    title.setText((document.getData()?.get("title")).toString())

                    tag.setText((document.getData()?.get("tag")).toString())

                    content.setText((document.getData()?.get("content")).toString())

                    author.setText(data!!["name"].toString())

                    createTime.setText((document.getData()?.get("createdTime")).toString())

                } else {

                    Log.d("Tinaa", "No such document")

                }
            } else {

                Log.d("Tinaa", "get failed with ", it.getException())

            }


        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addData() {

        val articles = FirebaseFirestore.getInstance().collection("articles")

        val document = articles.document()

        val data = hashMapOf(
            "author" to hashMapOf(

                "email" to "wayne@school.appworks.tw",

                "id" to "waynechen323",

                "name" to "AKA小安老師"
            ),

            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪   網笑︰靠頻值撐住女神氣場",

            "content" to "南韓歌手IU（李如恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美" +
                    "美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽青春時代玉女歌手」起復古穿搭造型，卻意外美出新境界。",

            "createdTime" to Calendar.getInstance().timeInMillis,

            "id" to document.id,

            "tag" to "Beauty"
        )

        document.set(data as Map<String, Any>)

        database.collection(articles.path).add(data as Map<String, Any>)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i("Tinaa", "insert success")
                } else {
                    Log.i("Tinaaa", "insert fail")
                }
            }
    }


}
