package com.example.internalstorage

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val context: Context, private var mUsers: MutableList<UserModel>, private val mRowLayout: Int) : RecyclerView.Adapter<MyAdapter.UserViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mUsers[position]
        holder.nom_prenom.text = user.nom_prenom
        holder.email.text = user.adresse_email
        holder.date_naissance.text = user.date_naissance
        holder.classe.text = user.classe
        holder.delete.setOnClickListener {
            showDeleteDialog(user)
        }
        holder.edit.setOnClickListener {
            editUser(user)
        }
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nom_prenom: TextView = itemView.findViewById(R.id.nomprenomF)
        var email: TextView = itemView.findViewById(R.id.emailF)
        var date_naissance: TextView = itemView.findViewById(R.id.date_naissanceF)
        var classe: TextView = itemView.findViewById(R.id.classeF)
        var delete: Button = itemView.findViewById(R.id.delete)
        var edit: Button = itemView.findViewById(R.id.edit)

    }

    fun showDeleteDialog(user: UserModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete user")
        builder.setMessage("Are you sure you want to delete this user?")
        builder.setPositiveButton("Yes") { dialog, which ->
            deleteUser(user)
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun deleteUser(user: UserModel) {
        println("user id: ${user.user_id}")
    }

    fun refreshUsers() {
        println("refreshing users")
    }

    fun editUser(user: UserModel){
        println("edit user")
    }


}
