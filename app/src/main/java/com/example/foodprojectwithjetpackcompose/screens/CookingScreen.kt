package com.example.foodprojectwithjetpackcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import kotlin.math.max
import kotlin.math.min
import com.example.foodprojectwithjetpackcompose.R
import com.example.foodprojectwithjetpackcompose.ui.theme.LightGray


val AppBarCollapseHeight = 56.dp
val AppBarExpendedHeight = 400.dp

@Composable
fun CookingScreen() {
    val scrollState = rememberLazyListState()
    ParallaxToolbar(scrollState)

}

@Composable
fun Content() {

}

@Composable
fun ParallaxToolbar(scrollState: LazyListState) {

    val imageHeight = AppBarExpendedHeight - AppBarCollapseHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset
    /** top lines exactly has written in the insets document*/
    /** offsetProgress: for gradually appbar fading*/

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color.White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        /**bottom box is for image's box: */
        Column {

            Box(
                modifier = Modifier
                    .height(imageHeight)
                    .graphicsLayer { 1f - offsetProgress }
                /** graphicsLayer: get the amount of fading and appearing*/

            ) {
                Image(
                    painter = painterResource(id = R.drawable.strawberry_pie_1),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                /**Implementation the dim color under the Image: */

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            /** 1- */
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Color.Transparent),
                                    Pair(1f, Color.White)
                                )
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Desert",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapseHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "StrawBerry cake",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }
    }
}

/**
 * 1- Brush.verticalGradient(
colorStops = arrayOf(
Pair(0.4f, Color.Transparent),     ===> this block's mean is you change the color from Color.Transparent until Color.White
Pair(1f, Color.White)
)
)*/