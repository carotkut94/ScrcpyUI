package ui

import MobileDevice
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
fun DevicesRow(devices:List<MobileDevice>, selectedDeviceIndex:Int, setShowDevices:(Boolean)->Unit, showDeviceMenu:Boolean, setSelectedDevice:(Int)->Unit){

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Text("Devices")

        if(devices.isNotEmpty()){
            DropdownMenu(toggle = {
                Text(devices[selectedDeviceIndex].productName.trim(), modifier = Modifier.clickable(onClick = { setShowDevices(true) }).padding(16.dp))
            },expanded = showDeviceMenu, onDismissRequest = {
                setShowDevices(false)
            }, dropdownModifier = Modifier.fillMaxWidth()){
                devices.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        onClick = {
                            setSelectedDevice(index)
                            setShowDevices(false)
                        }
                    ) {
                        Text(text = s.productName.trim())
                    }
                }
            }
        }else{
            Text("No Devices")
        }
    }
}