package com.example.mymaps

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymaps.models.Place
import com.example.mymaps.models.UserMap
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
private const val REQUEST_CODE = 1234
private const val TAG = "MapsAdapter"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userMaps = generateSampleData()
        // Set layout manager on the recycler view
        rvMaps.layoutManager = LinearLayoutManager(this)
        //Set adapter on the recycler view
        rvMaps.adapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                val intent = Intent(this@MainActivity, DisplayMapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)
            }

        })

        fabCreateMap.setOnClickListener{
            Log.i(TAG,"Tapped on FAB")
            val intent = Intent(this@MainActivity,CreateMapsActivity::class.java)
            intent.putExtra(EXTRA_MAP_TITLE, "NEW MAP NAME")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Memories from University",
                listOf(
                    Place("Caminul IV", "First year of uni", 46.756, 23.590),
                    Place("Baza sportiva Unirea", "Where the passion for basketball reignited", 46.748, 23.540),
                    Place("/Form space", "Where the concerts happened", 46.767, 23.572)
                )
            ),
            UserMap("January madness",
                listOf(
                    Place("Baia Borsa", "Ski Cabin", 47.611, 24.789),
                    Place("La Martuca", "NYE", 47.596, 23.760)
                )),
            UserMap("The summer of 2019",
                listOf(
                    Place("Baiut", "The land of the free souls", 47.624, 24.005),
                    Place("Electric Castle", "Where you can live the life you've always wanted", 46.909, 23.809),
                    Place("Untold", "How did I get here?", 46.768, 23.572)
                )
            )
        )
    }
}
