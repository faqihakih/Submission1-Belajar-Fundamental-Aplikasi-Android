package com.akih.submissionbfaa1.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akih.submissionbfaa1.adapter.GithubAdapter
import com.akih.submissionbfaa1.data.Avatar
import com.akih.submissionbfaa1.data.Github
import com.akih.submissionbfaa1.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listGithub: ArrayList<Github>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listGithub = ArrayList()

        try {
            val dataGithub = JSONObject(getJSONFromAssets()!!)
            val user = dataGithub.getJSONArray("users")

            for (i in 0 until user.length()){
                val listUser = user.getJSONObject(i)
                val name = listUser.getString("name")
                val username = listUser.getString("username")
                val avatar = Avatar.avatar[i]
                val company = listUser.getString("company")
                val location = listUser.getString("location")
                val repository = listUser.getInt("repository")
                val follower = listUser.getInt("follower")
                val following = listUser.getInt("following")
                val setUsers = Github(username, name,  avatar, company, location, repository, follower, following)
                listGithub.add(setUsers)
            }

        }
        catch (e : JSONException){
            e.printStackTrace()
        }
        showData()
    }

    companion object{
        const val EXTRA_DATA = "data"
    }

    private fun showData(){
        val userItem = GithubAdapter(listGithub)
        binding.rvGuthubList.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = userItem
        }
        userItem.setClick(object : GithubAdapter.setOnClickItemListerner{
            override fun setClickItem(github: Github) {
                val data = Github(github.username, github.name, github.avatar, github.company, github.location, github.repository, github.follower, github.following)
                startActivity(Intent(this@MainActivity, ProfileGithubActivity::class.java)
                        .putExtra(EXTRA_DATA, data)
                )
            }
        })
    }

    private fun getJSONFromAssets() :String? {
        var json : String? = null
        val charSet : Charset = Charsets.UTF_8

        try {
            val userJson = assets.open("githubuser.json")
            val size = userJson.available()
            val buffer = ByteArray(size)
            userJson.read(buffer)
            userJson.close()
            json = String(buffer, charSet)
        }catch (e : IOException){
            e.printStackTrace()
            return null
        }
        return json
    }
}