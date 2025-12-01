package tw.edu.pu.csim.tcyang.s1132238

import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun ExamScreen(viewModel: ExamViewModel = viewModel()) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels.toFloat()
    val height = displayMetrics.heightPixels.toFloat()

    val density = LocalDensity.current.density
    val imageSizeDp = (300 / density).dp


    val services = listOf(
        R.drawable.service0,
        R.drawable.service1,
        R.drawable.service2,
        R.drawable.service3
    )

    var currentService by remember { mutableStateOf(services.random()) }

    var serviceX by remember { mutableStateOf(width / 2) }
    var serviceY by remember { mutableStateOf(0f) }
    var message by remember { mutableStateOf("") }


    val role0Rect = Rect(0, (height / 2).toInt() - 300, 300, (height / 2).toInt())

    // 2. 兒童 (右邊, 螢幕中間上方)
    val role1Rect = Rect((width - 300).toInt(), (height / 2).toInt() - 300, width.toInt(), (height / 2).toInt())

    // 3. 成人 (左邊, 螢幕底部)
    val role2Rect = Rect(0, height.toInt() - 300, 300, height.toInt())

    // 4. 一般民眾 (右邊, 螢幕底部)
    val role3Rect = Rect((width - 300).toInt(), height.toInt() - 300, width.toInt(), height.toInt())


    fun resetService(msg: String) {
        message = msg
        serviceY = 0f
        serviceX = width / 2
        currentService = services.random()
    }

    LaunchedEffect(Unit) {

        serviceX = width / 2

        while (true) {
            delay(100)
            serviceY += 20

            val serviceRect = Rect(
                (serviceX - 150).toInt(),
                serviceY.toInt(),
                (serviceX + 150).toInt(),
                (serviceY + 300).toInt()
            )


            if (Rect.intersects(serviceRect, role0Rect)) {
                resetService("(碰撞嬰幼兒圖示)")
            }
            else if (Rect.intersects(serviceRect, role1Rect)) {
                resetService("(碰撞兒童圖示)")
            }
            else if (Rect.intersects(serviceRect, role2Rect)) {
                resetService("(碰撞成人圖示)")
            }
            else if (Rect.intersects(serviceRect, role3Rect)) {
                resetService("(碰撞一般民眾圖示)")
            }
            else if (serviceY > height) {
                resetService("(掉到最下方)")
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "Happy Image"
            )


            Text(
                text = "瑪利亞基金會服務大考驗",
                fontSize = 18.sp,

                )


            Text(
                text = "作者 : 資管二A 黃義祥",
                fontSize = 18.sp
            )


            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "螢幕大小 : $width * $height",
                fontSize = 18.sp
            )


            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "成績 : ${viewModel.score}分 $message",
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.role0),
                    contentDescription = "嬰兒",
                    modifier = Modifier.size(imageSizeDp).align(Alignment.BottomStart)
                )
                Image(
                    painter = painterResource(id = R.drawable.role1),
                    contentDescription = "兒童",
                    modifier = Modifier.size(imageSizeDp).align(Alignment.BottomEnd)
                )
            }
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.role2),
                    contentDescription = "成人",
                    modifier = Modifier.size(imageSizeDp).align(Alignment.BottomStart)
                )
                Image(
                    painter = painterResource(id = R.drawable.role3),
                    contentDescription = "一般民眾",
                    modifier = Modifier.size(imageSizeDp).align(Alignment.BottomEnd)
                )
            }
        }

        Image(
            painter = painterResource(id = currentService),
            contentDescription = "Service Icon",
            modifier = Modifier
                .size(imageSizeDp)
                .offset { IntOffset(serviceX.toInt() - 150, serviceY.toInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        serviceX += dragAmount.x
                    }
                }
        )
    }


}