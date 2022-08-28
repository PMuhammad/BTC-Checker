package com.example.btcchecker.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.btcchecker.R
import com.example.btcchecker.viewmodel.CryptoViewModel
import java.math.RoundingMode
import kotlin.math.roundToLong

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    vm: CryptoViewModel = viewModel()
){

    val btcPrice = vm.btcPrice.collectAsState()
    val btcChange = vm.btcChange.collectAsState()

    val animatedChange by animateFloatAsState(
        targetValue = btcChange.value.toFloat(),
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    val animatedPrice by animateIntAsState(
        targetValue = btcPrice.value,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    val animatedColor by animateColorAsState(
        targetValue =
        if (btcChange.value < 0) Color.Red else Color(0xFF08c227),
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Image(
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .drawBehind {
                    val center = size.center
                    val lineLength = size.width / 2f
                    drawLine(
                        color = Color(0xFFff7f0f),
                        start = Offset(center.x - lineLength, size.height),
                        end = Offset(center.x + lineLength, size.height),
                        strokeWidth = 10f,

                        )
                },
            painter = painterResource(id = R.drawable.btcicon),
            contentDescription = null
        )

        Column(
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                text = "$$animatedPrice",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
           Row(
               modifier = modifier
                   .fillMaxWidth()
                   .padding(20.dp)
                   ,
               horizontalArrangement = Arrangement.Center
           ) {
               Text(
                   modifier = modifier
                       .weight(1f)
                       .align(Alignment.CenterVertically),
                   text = "24h Change:",
                   fontSize = 20.sp,
                   textAlign = TextAlign.Center
               )
               Text(
                   modifier = modifier
                       .weight(1f)
                       .align(Alignment.CenterVertically),
                   text = "% ${animatedChange.toBigDecimal().setScale(2,RoundingMode.HALF_EVEN)}",
                   fontSize = 20.sp,
                   textAlign = TextAlign.Center,
                   color = animatedColor
               )
           }
        }

        Button(
            modifier = modifier
                .weight(1f, false)
                .align(Alignment.CenterHorizontally)
                .padding(90.dp)
                .shadow(20.dp)
                ,
            onClick = {
                vm.updateData()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF002f7a)
            )
        ) {
            Text(
                text = "Tap to update the price",
                color = Color.White
            )
        }

    }

}