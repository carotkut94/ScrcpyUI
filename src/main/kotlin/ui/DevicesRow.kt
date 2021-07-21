package ui

import MobileDevice
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DevicesRow(devices:List<MobileDevice>, selectedDeviceIndex:Int, setShowDevices:(Boolean)->Unit, showDeviceMenu:Boolean, setSelectedDevice:(Int)->Unit){

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Text("Devices")
        if(devices.isNotEmpty()){
            Box{
                Text(devices[selectedDeviceIndex].productName.trim(), modifier = Modifier.clickable(onClick = { setShowDevices(true) }).padding(16.dp))
                DropdownMenu(expanded = showDeviceMenu, onDismissRequest = {
                    setShowDevices(false)
                }, modifier = Modifier.fillMaxWidth()){

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
            }
        }else{
            Text("No Devices")
        }
    }
}

