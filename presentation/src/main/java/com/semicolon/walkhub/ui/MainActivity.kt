package com.semicolon.walkhub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nms_android_v1.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initBottomNav() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentActivityMain)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }

    override fun initView() {
        initBottomNav()
    }
}