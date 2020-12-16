package com.example.android.nasa_api

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var mYear = 0
    var mMonth: Int = 0
    var mDay: Int = 0
    var dateString: String? = null
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)

        //коли оновлюються дані в viewModel
        viewModel.nasaData().observe(this, Observer { event ->
            when (event.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    showData(event.data)
                }
                Status.ERROR -> {
                }
            }
        })
        //загружає сьогоднішній пост при вході в додаток
        viewModel.loadData()

        btn_date.setOnClickListener {
            changeImageData()
        }
    }
    //set in layout
    private fun showData(data: NasaData?) {
        if (data != null) {
            artTitle.text = data.title

            Picasso.get()
                .load(data.url)
                .into(photo_nasa)
        }
    }

    private fun changeImageData() {

        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                in_date.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                dateString = year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth + ""

                viewModel.loadData(dateString?: "")
            }, mYear, mMonth, mDay
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}