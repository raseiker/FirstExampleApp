package com.example.firstexampleapp.ui.utils

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.firstexampleapp.R
import java.util.*


@Composable
fun MyDatePicker(
    label: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    var date by remember {
        mutableStateOf("")
    }

    val datePicker = DatePickerDialog(
        context,
        R.style.DialogTheme,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            date = "$d/${m + 1}/$y"
        }, year, month, day
    )

    datePicker.datePicker.maxDate = calendar.timeInMillis
//    datePicker.datePicker

    MyTextFieldFormDate(
        label = label,
        textDate = date,
        onClickDate = {
            datePicker.show()
        },
        modifier = modifier
    )
}