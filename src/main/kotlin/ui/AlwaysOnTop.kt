package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlwaysOnTopItem(isAlwaysOnTop:Boolean, changeWindowMode:(Boolean)->Unit){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Text("Always On Top")
        Checkbox(checked = isAlwaysOnTop, onCheckedChange = {
            changeWindowMode(it)
        })
    }
}
