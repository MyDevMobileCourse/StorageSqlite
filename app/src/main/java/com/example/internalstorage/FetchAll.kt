package com.example.internalstorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internalstorage.*

class FetchAll : AppCompatActivity() {
    lateinit var reView: RecyclerView
    lateinit var mAdapter: MyAdapter
    lateinit var empty: CardView
    lateinit var button: Button
    lateinit var searchText: EditText
    lateinit var searchButton: Button
    var mUsers: MutableList<UserModel> = ArrayList<UserModel>()
    lateinit var userDbHelper: UserDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_all)
        init()
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        fetchUsers();
    }

    fun init() {
        empty = findViewById(R.id.empty)
        button = findViewById(R.id.load)
        searchText = findViewById(R.id.searchText)
        searchButton = findViewById(R.id.search)
        userDbHelper = UserDbHelper(this)
        searchButton.setOnClickListener {
            search()
        }
        initRecyclerView()
    }

    fun initRecyclerView() {
        reView = findViewById(R.id.recyclerview) as RecyclerView
        reView.layoutManager = LinearLayoutManager(this)
        mAdapter = MyAdapter(this, mUsers, R.layout.user_item)
        reView.adapter = mAdapter
        reView.visibility = View.INVISIBLE
        empty.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Quit");
        alertDialogBuilder.setMessage("are you sure you want to quit ?  ?");
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        };
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Canceled", Toast.LENGTH_SHORT
            ).show()
        };
        alertDialogBuilder.show();
    }

    private fun fetchUsers() {
        println("fetching users")
        var users = userDbHelper.readUsers()
        println(users)
        if (users.size > 0) {
            println("users found")
            mUsers.clear()
            mUsers.addAll(users)
            reView.visibility = View.VISIBLE
            empty.visibility = View.INVISIBLE
            mAdapter.notifyDataSetChanged()
        } else {
            println("no users found")
            mUsers.clear()
            reView.visibility = View.INVISIBLE
            empty.visibility = View.VISIBLE
            mAdapter.notifyDataSetChanged()

        }
    }

    fun search() {

        val search = searchText.text.toString()
       
        mUsers = userDbHelper.searchUsers(search)
        if (mUsers.size > 0) {
            reView.visibility = View.VISIBLE
            empty.visibility = View.INVISIBLE
            mAdapter.notifyDataSetChanged()
        } else {
            reView.visibility = View.INVISIBLE
            empty.visibility = View.VISIBLE
            mAdapter.notifyDataSetChanged()

        }

    }


}