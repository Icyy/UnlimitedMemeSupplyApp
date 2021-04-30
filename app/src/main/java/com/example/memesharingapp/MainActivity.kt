package com.example.memesharingapp

import MySingleton
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var url : String? = null
    var subName :String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subName = intent.getStringExtra("Subs")
        Log.d("$subName", "This was the sub name: $subName")
        loadMeme()

    }

    fun loadMeme(){
        loadingButton.visibility = View.VISIBLE


        url = "https://meme-api.herokuapp.com/gimme/$subName"

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                val url = response.getString("url")
                Glide.with(this).load(url).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadingButton.visibility = View.GONE //To change body of created functions use File | Settings | File Templates.
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loadingButton.visibility = View.GONE
                        return false//To change body of created functions use File | Settings | File Templates.
                    }
                }).into(memeImage)



            },
            Response.ErrorListener {

                Log.d("some thing happened!", it.localizedMessage)

            }



        )
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest)


    }


    fun shareMeme(view: View) {
      val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Here share it with your friends $url")
        val chooser = Intent.createChooser(intent, "Share this using:")
        startActivity(chooser)


    }
    fun nextMeme(view: View) {
        loadMeme()

    }

    fun goBack(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
