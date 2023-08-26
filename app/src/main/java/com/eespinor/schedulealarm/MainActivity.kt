package com.eespinor.schedulealarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eespinor.schedulealarm.ui.theme.ScheduleAlarmTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val schedule = AndroidAlarmSchedule(this)
        var alarmItem: AlarmItem? = null
        setContent {
            ScheduleAlarmTheme {
                var secondsText by remember {
                    mutableStateOf("")
                }
                var message by remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = secondsText,
                        onValueChange = { secondsText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Trigger alarm in seconds")
                        }
                    )
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Message")
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            alarmItem = AlarmItem(
                                time = LocalDateTime.now()
                                    .plusSeconds(secondsText.toLong()),
                                message = message

                            )

                            alarmItem?.let(schedule::schedule)
                            secondsText = ""
                            message = ""
                        }) {
                            Text(text = "Schedule")
                        }
                        Button(onClick = {
                            alarmItem?.let ( schedule::cancel )
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}