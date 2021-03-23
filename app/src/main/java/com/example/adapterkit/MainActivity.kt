package com.example.adapterkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.adapterkit.old.OldRecyclerviewActivity
import com.example.adapterkit.xin.activity.NewRecyclerviewActivity

class MainActivity : AppCompatActivity() , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_i_new_recyclerview->{
                startActivity(Intent(this, NewRecyclerviewActivity::class.java))
            }

            R.id.tv_i_old_recyclerview->{
                startActivity(Intent(this, OldRecyclerviewActivity::class.java))
            }

        }
    }
}