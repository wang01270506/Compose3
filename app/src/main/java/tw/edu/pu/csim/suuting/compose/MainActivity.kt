package tw.edu.pu.csim.suuting.compose



import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.suuting.compose.ui.theme.ComposeTheme
import tw.edu.pu.csim.suuting.compose.ui.theme.Pink40
import tw.edu.pu.csim.suuting.compose.ui.theme.Pink80
import tw.edu.pu.csim.suuting.compose.ui.theme.PurpleGrey40
import tw.edu.pu.csim.suuting.compose.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("王思婷簡介App")
                }
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String) {
    var X = remember { mutableStateOf(0f) }
    var Y = remember { mutableStateOf(0f) }
    val mouse = ImageBitmap.imageResource(id = R.drawable.mouse)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter { event ->
                X.value = event.getX(0)
                Y.value = event.getY(0)
                true
            }

    ){
        Canvas(modifier = Modifier){
            //drawCircle(Color.Yellow, 50f, Offset(X.value, Y.value))
            drawImage(mouse, Offset(X.value-mouse.width/2,Y.value-mouse.height/2))
    }
    }

    Column{
        Row {
            Text(text = "$name",color= Color.Gray, fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.kai)))

            Image(
                painter = painterResource(id = R.drawable.helen),
                contentDescription = "簡介照片"
            )
        }

        Text(text = "作者:資管系-王思婷,年齡:20,水瓶座", color = PurpleGrey80)
        Text(text = "名言佳句:", color = Pink80)
        Text(text = "人生就是生活的過程。哪能沒有風沒有雨？正是因為有了風雨的洗禮才能看見斑斕的彩虹；有了失敗的痛苦才會嘗到成功的喜悅。", color = Pink80)

        val click_text = remember { mutableStateOf("") }
        val interactionSource = remember { MutableInteractionSource() }
        val interactions = remember { mutableStateListOf<Interaction>() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val isPressedOrDragged = interactions.isNotEmpty()

        Button(
            onClick = { println("點擊了按鈕") },
            interactionSource = interactionSource) {
            Text("按我!，自我介紹")
        }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        interactions.add(interaction)
                    }
                    is PressInteraction.Release -> {
                        interactions.remove(interaction.press)
                    }
                    is PressInteraction.Cancel -> {
                        interactions.remove(interaction.press)
                    }
                    is DragInteraction.Start -> {
                        interactions.add(interaction)
                    }
                    is DragInteraction.Stop -> {
                        interactions.remove(interaction.start)
                    }
                    is DragInteraction.Cancel -> {
                        interactions.remove(interaction.start)
                    }
                }
            }
        }
        val lastInteraction = when (interactions.lastOrNull()) {
            is DragInteraction.Start -> "Dragged"
            is PressInteraction.Press -> "Pressed"
            else -> "No state"
        }
        }
    }







//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
    //ComposeTheme {
       // Greeting("王思婷個人簡介App")
    //}
//}