package com.raihan.myapplication.jadwalsholat

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityJadwalSholatBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class JadwalSholatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalSholatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initView()

        getPrayTimeData("dkijakarta")

    }

    private fun initView() {
        val waktu: Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("E, dd MMM", Locale.getDefault())
        val formattedDate = dateFormat.format(waktu)

        binding.tvDatePray.text = formattedDate

    }

    private fun getPrayTimeData(location: String) {
        val client = AsyncHttpClient()
        val url =
            "https://muslimsalat.com/$location/daily.json?apikey=a2f23d1de444fe6a81a0b9eecd94668d"

       client.get(url, object : AsyncHttpResponseHandler() {
           override fun onSuccess(
               statusCode: Int,
               headers: Array<out Header>?,
               responseBody: ByteArray?
           ) {
               // tampilkan data
               binding.pbJadwalSholat.visibility = View.INVISIBLE
               val responData = responseBody?.let { String(it) }

               // parsing json (menguraikan data json)
               try {
                   // 1. mengambil seluruh data
                   val responObject = JSONObject(responData)

                   // 2. mengambil array jadwal sholat dengan nama "items"
                   val prayTimeArray = responObject.getJSONArray("items")

                   // cek apakah data terambil
                   Log.d("Jadwal Sholat", "onSuccess: $prayTimeArray")

                   // 3. mengambil json object kedalam json Array dengan urutan ke 0
                   val prayTime = prayTimeArray.getJSONObject(0)

                   // 4. tampilkan data dalam "items" ke dalam komponen UI
                   binding.tvPrayTimeSubuh.text = prayTime.getString("fajr")
                   binding.tvPrayTimeSunrise.text = prayTime.getString("shurooq")
                   binding.tvPrayTimeDzuhur.text = prayTime.getString("dhuhr")
                   binding.tvPrayTimeAshar.text = prayTime.getString("asr")
                   binding.tvPrayTimeMaghrib.text = prayTime.getString("maghrib")
                   binding.tvPrayTimeIsya.text = prayTime.getString("isha")

                   binding.tvLocation.text = responObject.getString("title")


               } catch (error: Exception){
                   Toast.makeText(this@JadwalSholatActivity, error.message, Toast.LENGTH_SHORT).show()

               }

           }

           override fun onFailure(
               statusCode: Int,
               headers: Array<out Header>?,
               responseBody: ByteArray?,
               error: Throwable?
           ) {
               // tampilkan pesan error
               Toast.makeText(this@JadwalSholatActivity, error?.message, Toast.LENGTH_SHORT).show()
           }

       })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {

            }
        }
        return super.onOptionsItemSelected(item)

    }
}
