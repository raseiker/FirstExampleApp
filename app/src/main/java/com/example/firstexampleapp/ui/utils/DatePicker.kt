package com.example.firstexampleapp.ui.utils

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.firstexampleapp.R
import java.util.*


@Composable
fun MyDatePicker(
    label: String,
    text: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    //var date by remember { mutableStateOf("") }

    val datePicker = DatePickerDialog(
        context,
        R.style.DialogTheme,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            //date = "$d/${m + 1}/$y"
            onValueChange("$d/${m + 1}/$y")//pass up latest date
        }, year, month, day
    )

    datePicker.datePicker.maxDate = calendar.timeInMillis

    MyTextFieldFormDate(
        label = label,
        textDate = text,
        onClickDate = { datePicker.show() },
        modifier = modifier
    )
}