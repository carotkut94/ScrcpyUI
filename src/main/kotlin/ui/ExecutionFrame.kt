import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Notifier
import ui.*

@Composable
fun ExecutionFrame(
    deviceOptions:List<MobileDevice>,
    minimise:()->(Unit)
) {
    val (showDialog, setShowDialog) =  remember { mutableStateOf(false) }
    val (selectedFps, changeFps) =  remember { mutableStateOf(60f) }
    val (isAlwaysOnTop, changeWindowMode) =  remember { mutableStateOf(false) }
    val (canRunMultipleInstance, changeRunMode) =  remember { mutableStateOf(false) }

    val topCommand = "--always-on-top"

    //val notifier = Notifier()
    val topComputedCommand = derivedStateOf { if(isAlwaysOnTop) topCommand else "" }
    val (selectedResolutionIndex, setResolutionIndex) = remember{ mutableStateOf(0) }

    val (showMenu, setShowMenu) = remember { mutableStateOf( false ) }
    val (showDeviceMenu, setShowDeviceMenu) = remember { mutableStateOf( false ) }

    var p:Process? = null

    val resolutionItems = listOf("720", "1024", "1920", "2280")

    val (selectedDevice, setSelectedDevice) = remember{ mutableStateOf(0) }

    Box{
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            
            DevicesRow(deviceOptions, selectedDevice, setShowDeviceMenu, showDeviceMenu, setSelectedDevice)

            ResolutionRow(resolutionItems, selectedResolutionIndex, setShowMenu, showMenu,setResolutionIndex)

            CanRunMultipleInstance(canRunMultipleInstance, changeRunMode)

            AlwaysOnTopItem(isAlwaysOnTop, changeWindowMode)
            Slider(value = selectedFps,onValueChange = {
                changeFps(it)
            }, valueRange = 10f..100f, modifier = Modifier.padding(16.dp))
            Text("FPS : ${selectedFps.toInt()}")

            Button(
                onClick = {
                    if(deviceOptions.isNotEmpty()) {
                        try {
                            if (!canRunMultipleInstance && p != null) {
                                p!!.destroy()
                            }
                            p = Runtime.getRuntime()
                                .exec("scrcpy -s ${deviceOptions[selectedDevice].productId} --max-fps ${selectedFps.toInt()} ${topComputedCommand.value} -m${resolutionItems[selectedResolutionIndex]}")
                            minimise()
                            //notifier.notify("Alert", "scrcpy started")
                        } catch (e: Exception) {
                            e.printStackTrace()
                            setShowDialog(true)
                        }
                    }else{
                        //notifier.notify("Alert", "No Devices Connected")
                    }
                }, modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text(if(deviceOptions.isEmpty()) "No Devices" else "Start")
            }

            ExecutableNotFoundDialog(showDialog, setShowDialog)
        }
    }
}

