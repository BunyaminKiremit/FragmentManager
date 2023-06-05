package com.example.fragmentmanager

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btnAdd: Button
    private lateinit var btnRemove: Button

    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()
    private val threeFragment = ThreeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Layout dosyasında tanımlı düğmeleri ilgili değişkenlere atama
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btnAdd = findViewById(R.id.btnAdd)
        btnRemove = findViewById(R.id.btnRemove)

        setupClickListeners()

        // Başlangıçta "OneFragment"i yerleştirme
        performFragmentTransaction(oneFragment)
    }

    private fun setupClickListeners() {
        btn1.setOnClickListener {
            performFragmentTransaction(oneFragment)
        }

        btn2.setOnClickListener {
            performFragmentTransaction(twoFragment)
        }

        btn3.setOnClickListener {
            performFragmentTransaction(threeFragment)
        }

        btnAdd.setOnClickListener {
            addFragment(twoFragment)
        }

        btnRemove.setOnClickListener {
            removeFragment(twoFragment)
        }
    }

    // Fragment yerleştirme işlemini gerçekleştiren fonksiyon
    private fun performFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    // Fragment eklemeyi gerçekleştiren fonksiyon
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout2, fragment)
            .commit()
    }

    // Fragment kaldırmayı gerçekleştiren fonksiyon
    private fun removeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Çıkış")
            .setMessage("Uygulamadan çıkmak istediğinizden emin misiniz?")
            .setPositiveButton("Evet") { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton("Hayır", null)
            .create()
            .show()
    }
}