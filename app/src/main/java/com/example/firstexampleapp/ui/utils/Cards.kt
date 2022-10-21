package com.example.firstexampleapp.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme

//@Preview(showBackground = true, device = Devices.NEXUS_5X)
//@ExperimentalMaterialApi
//@Composable
//fun MyCardsPreview() {
//
//    FirstExampleAppTheme() {
//        Scaffold(
//        )
//        {
//            Column(modifier = Modifier.padding(paddingValues = it)) {
////                MyNormalCard(
////                    title = "Calculadora de alimentos",
////                    subTitle = "Identifica los alimentos mas apropiados para ti",
////                    icon = R.drawable.ic_baseline_access_time_filled_24,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyNormalCard(
////                    title = "Calculadora de alimentos",
////                    subTitle = "Identifica los alimentos mas apropiados para ti",
////                    icon = R.drawable.ic_baseline_access_time_filled_24,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyArticleItemCard(
////                    title = "Vitaminas y minerales a consumir con mas frecuencia",
////                    subTitle = "Las vitaminas y minerales desempeñan una funcion muy importante para el desarrollo",
////                    image = R.mipmap.wiegth,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////                MyArticleItemCard(
////                    title = "Una dieta equilibrada, clave durante el embarazo",
////                    subTitle = "La alimentación durante el embarazo ha de ser sana y equilibrada: tan perjudicial",
////                    image = R.mipmap.fruits,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////                MyArticleCard(
////                    title = "Como me ayudan las vitaminas y minerales",
////                    subTitle = "Las vitaminas y minerales desarrollan una función muy importante para el desarrollo de todas tus funciones",
////                    image = R.mipmap.wiegth,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
//
//                MyExpandibleNormalCard(
//                    //title = "Calculadora de alimentos",
//                    question = "Tengo antojos de alimentos pocos saludables. ¿Esto es normal?",
//                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
//                )
//                MyExpandibleNormalCard(
//                    //title = "Calculadora de alimentos",
//                    question = "No estoy ganando nada de peso. ¿Deberia cambiar mi dieta?",
//                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
//                )
//
//                Column() {
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        MyToolCard(
//                            title = "Calculadora de alimentos",
//                            subTitle = "Identifica las calorías exactas",
//                            image = R.mipmap.vector_weight,
//                            modifier = Modifier.padding(start = 20.dp, end = 10.dp, top = 10.dp)
//                        )
//                        MyToolCard(
//                            title = "Recomendaciones",
//                            subTitle = "Que comer segun tu edad gestacional",
//                            image = R.mipmap.vector_recommendations,
//                            modifier = Modifier.padding(start = 10.dp, end = 20.dp, top = 10.dp)
//                        )
//                    }
//                }
//
////                MyCheckBoxCard(
////                    task = "Preguntarle al nutriólogo sobre el mercurio en el pescado",
////                    isDone = false,
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
//
//                MyProgressIndicatorCard(
//                    title = "13 semanas de embarazo",
//                    subTitle = "1er Trimestre",
//                    progress = 0.3f,
//                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
//                )
//            }
//        }
//    }
//}


@ExperimentalMaterialApi
@Composable
fun MyNormalCard(
    title: String,
    subTitle: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,//IR A LA ACCION O PAGINA
        elevation = 5.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                //  .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 15.dp)//INTERNAL CHILDREN ELEMENTS PADDING
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(weight = 2f)
            ) {
                Row(
                    modifier = Modifier//THIS ROW DRAW A CIRCLE
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.secondary,
                            shape = CircleShape
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.secondary
                    )
                }
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        //fontWeight = FontWeight.Medium,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                contentDescription = "",
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MyArticleItemCard(
    title: String,
    subTitle: String,
    onClick: () -> Unit,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,// to action
        elevation = 5.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                //  .fillMaxWidth()
                .padding(end = 12.dp)//INTERNAL CHILDREN ELEMENTS PADDING
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(weight = 2f)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.size(90.dp),//before 100
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        maxLines = 2
                    )
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }
            Icon(
                painter = painterResource(R.drawable.ic_baseline_open_in_full_24),
                contentDescription = "",
                modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .padding(bottom = 8.dp),
                tint = Color.Gray
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MyArticleCard(
    title: String,
    subTitle: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    badges: List<String> = listOf("ARTÍCULOS", "ESENCIALES"),
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,// to action
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    maxLines = 2
                )
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Row(
                        modifier = Modifier.weight(weight = 2f)
                    ) {
                        MyBadge(text = badges[0])
                        MyBadge(text = badges[1])
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_open_in_full_24),
                        contentDescription = "",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MyExpandibleNormalCard(
    question: String,
    answer: String,
    onValueChange: (String) -> Unit,
    onClearText: () -> Unit,
    onSendClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /*TODO*/ },//IR A LA ACCION O PAGINA
        elevation = 5.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp))
    ) {
        var isExpanded by remember { mutableStateOf(value = false) }
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    //.fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 15.dp
                    )//INTERNAL CHILDREN ELEMENTS PADDING
            ) {
                Row(
                    modifier = Modifier//THIS ROW DRAW A CIRCLE
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.secondary,
                            shape = CircleShape
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_circle_question_solid),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.secondary
                    )
                }
                Text(
                    text = question,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Light),
                    maxLines = 4,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f)
                )
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .wrapContentWidth(Alignment.End)
                ) {
                    Icon(
                        painter = if (isExpanded) painterResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        else painterResource(R.drawable.ic_baseline_keyboard_arrow_down_24),
                        contentDescription = "",
                    )
                }
            }
            if (isExpanded) {
                MyTextFieldFormQuestion(
                    text = answer,
                    onValueChange = onValueChange,
                    onClearText = onClearText,
                    onSendClicked = onSendClicked
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MyToolCard(
    title: String,
    subTitle: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        elevation = 5.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp))
    ) {
        Column(modifier = Modifier.width(Dp(160f))) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier/*.size(width = 160.dp, height = 120.dp)*/.aspectRatio(ratio = 2f / 1.5f)
            )
            Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    maxLines = 2
                )
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    ),
                    maxLines = 2,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

        }

    }
}

@ExperimentalMaterialApi
@Composable
fun MyCheckBoxCard(
    task: String,
    isDone: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
//    var isChecked by remember {
//        mutableStateOf(isDone)
//    }
    Card(
        onClick = {},//IR A LA ACCION O PAGINA
        elevation = 5.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        ) {
            Checkbox(
                checked = isDone,
                onCheckedChange = onCheckedChange,
            )
            Text(
                text = task,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Light),
                maxLines = 4,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

    }
}

@ExperimentalMaterialApi
@Composable
fun MyProgressIndicatorCard(
    title: String,
    trimester: String,
    progress: Float,
    tint: Color? = MaterialTheme.colors.secondaryVariant,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {},//IR A LA ACCION O PAGINA
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
            )
            Text(
                text = trimester,
                style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Light),
                maxLines = 1,
                modifier = Modifier.offset(y = (-5).dp)
            )
            LinearProgressIndicator(
                progress = progress,
                color = tint ?: MaterialTheme.colors.secondaryVariant,
                backgroundColor = tint?.copy(alpha = 0.3f) ?: MaterialTheme.colors.secondaryVariant.copy(alpha = 0.3f),
                modifier = Modifier
                    .height(10.dp)
                    .clip(shape = CircleShape)
                    .fillMaxWidth()
            )
        }

    }
}