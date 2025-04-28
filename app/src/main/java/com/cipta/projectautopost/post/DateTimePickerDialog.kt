package com.cipta.projectautopost.post

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DateTimePickerDialog(
    onDateTimeSelected: (Calendar) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    // Ingat posisi sekarang (tahun, bulan, hari)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Tampilkan DatePicker dulu
    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            // Setelah tanggal dipilih, baru buka TimePicker
            TimePickerDialog(
                context,
                { _, selectedHour, selectedMinute ->
                    // Setelah jam dipilih
                    calendar.set(selectedYear, selectedMonth, selectedDayOfMonth, selectedHour, selectedMinute)
                    onDateTimeSelected(calendar) // Callback hasil pilihan
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        },
        year,
        month,
        day
    ).apply {
        setOnCancelListener { onDismiss() }
        setOnDismissListener { onDismiss() }
    }.show()
}
