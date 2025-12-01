package tw.edu.pu.csim.tcyang.s1132238

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExamScreen(viewModel: ExamViewModel = viewModel()) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels.toFloat()
    val height = displayMetrics.heightPixels.toFloat()

    val density = LocalDensity.current.density
    val imageSizeDp = (300 / density).dp


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
                text = "成績 : ${viewModel.score}分",
                fontSize = 18.sp
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 上半部區域 (佔 50% 高度)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // 分配權重 1
            ) {
                // role0 嬰兒：左下角 (螢幕中間左側)
                Image(
                    painter = painterResource(id = R.drawable.role0),
                    contentDescription = "嬰兒",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .align(Alignment.BottomStart)
                )
                // role1 兒童：右下角 (螢幕中間右側)
                Image(
                    painter = painterResource(id = R.drawable.role1),
                    contentDescription = "兒童",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .align(Alignment.BottomEnd)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // 分配權重 1
            ) {
                // role2 成人：左下角 (螢幕底部左側)
                Image(
                    painter = painterResource(id = R.drawable.role2),
                    contentDescription = "成人",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .align(Alignment.BottomStart)
                )
                // role3 一般民眾：右下角 (螢幕底部右側)
                Image(
                    painter = painterResource(id = R.drawable.role3),
                    contentDescription = "一般民眾",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .align(Alignment.BottomEnd)
                )
            }


        }
    }


}