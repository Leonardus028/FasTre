@file:Suppress("DEPRECATION")

package com.example.fastre.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fastre.databinding.FragmentProfileBinding
import com.example.fastre.ui.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: FirebaseUser
    private lateinit var reference: DatabaseReference
    private lateinit var userID: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        userID = user.uid

        val progressBar : ProgressBar = binding.progressBar
        val fullNameTextView: TextView = binding.tvName
        val emailTextView: TextView = binding.hintEmail
        val ageTextView: TextView = binding.hintAge
        val bloodTypeTextView: TextView = binding.hintBloodType

        reference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(
                    User::class.java
                )
                progressBar.visibility = View.VISIBLE
                if (userProfile != null) {
                    val fullName = userProfile.fullName
                    val email = userProfile.email
                    val age = userProfile.age
                    val bloodType = userProfile.bloodType
                    fullNameTextView.text = fullName
                    emailTextView.text = email
                    ageTextView.text = age
                    bloodTypeTextView.text = bloodType
                    progressBar.visibility = View.GONE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Something wrong Happened!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return binding.root
    }

}