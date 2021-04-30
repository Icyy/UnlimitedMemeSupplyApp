package com.example.memesharingapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity





class HomeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var subName :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.sub_names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
    }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            subName = parent.getItemAtPosition(pos).toString()
            (parent.getChildAt(0) as TextView).setTextColor(Color.BLACK)



            Log.d("$subName", "This was the subName: $subName")
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }



        fun goNext(view: View) {

//              Toast.makeText(this, "this is the subname $subName", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Subs", subName)
            startActivity(intent)
        }



}
