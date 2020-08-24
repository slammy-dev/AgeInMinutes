package tech.kotelnikov.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate.setOnClickListener { clickDatePicker(it) }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val selectedDate = sdf.parse(selectedDateText)

                val ageInMinutes = (Date().time - selectedDate!!.time) / 60000

                tvSelectedDate.text = selectedDateText
                tvSelectedDateInMinutes.text = "$ageInMinutes"
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = Date().time - 8640000
        datePickerDialog.show()
    }
}