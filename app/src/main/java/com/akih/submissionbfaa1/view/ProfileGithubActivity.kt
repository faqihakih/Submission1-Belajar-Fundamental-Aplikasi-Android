package com.akih.submissionbfaa1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akih.submissionbfaa1.data.Github
import com.akih.submissionbfaa1.databinding.ActivityProfileGithubBinding

class ProfileGithubActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileGithubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataList()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun dataList(){
        val data = intent.getParcelableExtra<Github>(MainActivity.EXTRA_DATA)
        binding.imgUser.setImageResource(data?.avatar!!)
        binding.tvName.text = data.name
        binding.tvUsername.text = data.username
        binding.tvCompany.text = data.company
        binding.tvLocation.text = data.location
        binding.tvRepo.text = data.repository.toString()
        binding.tvFollower.text = data.follower.toString()
        binding.tvFollowing.text = data.following.toString()
    }
}