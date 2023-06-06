package com.example.projectbaru18411054

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbaru18411054.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {
    private lateinit var sejarahRecyclerview: RecyclerView
    private lateinit var sejarahList: MutableList<Image>
    private lateinit var sejarahAdapter:MyAdapter
    private lateinit var binding: ActivityMainBinding

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**initialized*/
        sejarahRecyclerview = findViewById(R.id.myrecyclerView)
        sejarahRecyclerview.setHasFixedSize(true)
        sejarahRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        sejarahList = ArrayList()
        sejarahAdapter = MyAdapter(this@MainActivity,sejarahList)
        sejarahRecyclerview.adapter = sejarahAdapter
        /**getData firebase*/

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Sejarah")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                sejarahList.clear()
                if (snapshot.exists()){
                    for (teacherSnapshot in snapshot.children){
                        val upload = teacherSnapshot.getValue(Image::class.java)
                        upload!!.key = teacherSnapshot.key
                        sejarahList.add(upload)
                    }
                    sejarahAdapter.notifyDataSetChanged()

                }
            }
        })
        binding.btnBack.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                Intent(this, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
        return true
    }
}
