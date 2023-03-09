package com.example.foodprojectwithjetpackcompose.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
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
import com.example.foodprojectwithjetpackcompose.ui.theme.DarkGray
import com.example.foodprojectwithjetpackcompose.ui.theme.LightGray
import com.example.foodprojectwithjetpackcompose.ui.theme.Pink
import com.example.foodprojectwithjetpackcompose.ui.theme.Shapes
import com.google.accompanist.insets.statusBarsPadding


val AppBarCollapseHeight = 56.dp
val AppBarExpendedHeight = 400.dp

@Composable
fun CookingScreen() {
    val scrollState = rememberLazyListState()
    Box {
        ParallaxToolbar(scrollState = scrollState)
        Content(scrollState = scrollState)
    }
}

@Composable
fun Content(scrollState: LazyListState) {

    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        state = scrollState
    ) {
        item {
            BasicInfo()
            Description()
            ServingCalculator()
            ShoppingListButton()
            SimilarFoodsHeader()
            SimilarFoods()
        }
    }
}


@Composable
fun SimilarFoods() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        FastFoodItem(
            img = painterResource(id = R.drawable.hot_dog),
            name = "Hot Dog",
            desc = "Fast Foods",
            price = "45$"
        )

        FastFoodItem(
            img = painterResource(id = R.drawable.doughnut),
            name = "Doughnut",
            desc = "Dessert",
            price = "32$"
        )

        FastFoodItem(
            img = painterResource(id = R.drawable.hamburger),
            name = "Hamburger",
            desc = "Fast Foods",
            price = "56$"
        )

        FastFoodItem(
            img = painterResource(id = R.drawable.apple_pie),
            name = "Apple Pie",
            desc = "Cookies",
            price = "26$"
        )
    }
}


@Composable
fun FastFoodItem(
    img: Painter,
    name: String,
    desc: String,
    price: String
) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .height(250.dp)
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .clip(RoundedCornerShape(16.dp))
                .background(LightGray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = name
                )

                Text(
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 5.dp),
                    text = desc
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = price,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Pink
                    )

                    Button(
                        onClick = { },
                        contentPadding = PaddingValues(),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxHeight()
                            .width(38.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Pink,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(painterResource(id = R.drawable.ic_plus), contentDescription = "")
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = img,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
        }
    }
}

@Composable
fun SimilarFoodsHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = "Similar Foods", fontWeight = FontWeight.Bold)
            Text(text = "You may like these...", color = DarkGray)
        }

        Button(
            onClick = {},
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent, contentColor = Pink
            )
        ) {
            Text(text = "Show more")
            Icon(painterResource(id = R.drawable.ic_arrow_right), contentDescription = "")
        }

    }
}

@Composable
fun ShoppingListButton() {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {},
            elevation = null,
            shape = Shapes.small,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Pink,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add to shopping list", modifier = Modifier.padding(8.dp))
        }
    }
}


@Composable
fun ServingCalculator() {
    var value by remember {
        mutableStateOf(5)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(LightGray)
            .clip(Shapes.small)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Serving",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        CircularButton(iconRes = R.drawable.ic_minus, elevation = null, color = Pink) {
            value--
        }
        Text(text = "$value", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
        CircularButton(iconRes = R.drawable.ic_plus, elevation = null, color = Pink) {
            value++
        }
    }
}


@Composable
fun Description() {
    Text(
        text = "This Dessert is very tasty and not very hard to prepare. and please pay attention that you can replace strawberry with any another fruit you like",
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun BasicInfo() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, "60 min")
        InfoColumn(R.drawable.ic_flame, "735 kcal")
        InfoColumn(R.drawable.ic_star, "4.7")
    }
}

@Composable
fun InfoColumn(@DrawableRes iconRes: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "",
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )

        Text(text = text, fontWeight = FontWeight.Bold)
    }
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
            .height(AppBarExpendedHeight)
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            /**3-*/
            .height(AppBarCollapseHeight)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularButton(iconRes = R.drawable.ic_arrow_back)
        CircularButton(iconRes = R.drawable.ic_favorite)
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconRes: Int,
    color: Color = Color.Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)

    ) {
        Icon(painterResource(id = iconRes), null)
    }
}


/**
 * 1- Brush.verticalGradient(
colorStops = arrayOf(
Pair(0.4f, Color.Transparent),     ===> this block's mean is you change the color from Color.Transparent until Color.White
Pair(1f, Color.White)
))

2- onClick: () -> Unit = {}   === > we write like this until we can pass our onClick out side of that.
3- .statusBarsPadding()    ===> it has a default padding in the amount of statsBar
4- contentAlignment = Alignment.BottomCenter   ===> we want to add another box to this box but start with end off parent box
5- */