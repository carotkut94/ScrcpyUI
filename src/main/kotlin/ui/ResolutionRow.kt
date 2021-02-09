package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResolutionRow(resolutionItems:List<String>, selectedResolutionIndex:Int, setShowMenu:(Boolean)->Unit, showMenu:Boolean, setResolutionIndex:(Int)->Unit){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Text("Max Size")

        DropdownMenu(toggle = {
            Text(resolutionItems[selectedResolutionIndex], modifier = Modifier.clickable(onClick = { setShowMenu(true) }).padding(16.dp))
        },expanded = showMenu, onDismissRequest = {
            setShowMenu(false)
        }, dropdownModifier = Modifier.fillMaxWidth()){
            resolutionItems.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        setResolutionIndex(index)
                        setShowMenu(false)
                    }
                ) {
                    Text(text = s)
                }
            }
        }
    }
}