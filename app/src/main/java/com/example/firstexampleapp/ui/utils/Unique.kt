package com.example.firstexampleapp.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme

//@OptIn(ExperimentalUnitApi::class)
//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun MyUniquePreview() {
//    FirstExampleAppTheme() {
//        Scaffold(
//            floatingActionButton = {
//                MyFab()
//            }
//        ) {
//            Column(modifier = Modifier.padding(paddingValues = it)) {
////                MyImageHeader(
////                    image = R.mipmap.fruits,
////                )
////
////                MyDivider(
////                    text = "o",
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyArticlesCategoriesHeader(
////                    subtitle = "Bebidas y Comidas",
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyArticlesCategories(
////                    image = R.mipmap.wiegth,
////                    subtitle = "11 articles",
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyScreenTitle(
////                    title = "Explora",
////                    subTitle = "Todo lo que necesita saber",
////                    letter = "p",
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
////
////                MyWelcomeMessage(
////                    userName = "Carmela",
////                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
////                )
//                MyArticleText(
//                    text = "Estos ácidos tienen un importante efecto protector ante las enfermedades del corazón y en el desarrollo" +
//                            " del cerebro, el sistema nervioso y la visión en el bebé, tanto antes como después de su nacimiento. " +
//                            "Para beneficiarte al máximo de su potencial, deberías tomar por lo menos dos raciones de pescado o " +
//                            "marisco por semana, tanto durante el embarazo como durante la lactancia. " +
//                            "\n\nA pesar de los beneficios de " +
//                            "tomar pescado durante el embarazo, debes tener en cuenta que ciertos tipos de pescado pueden contener " +
//                            "una mayor concentración de mercurio, un metal que ha sido relacionado con la aparición de defectos de " +
//                            "nacimiento. Para limitar tu exposición al mercurio y poder disfrutar del aporte nutricional del pescado " +
//                            "con seguridad, sigue la siguiente recomendación: ",
//                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
//                )
//
//            }
//        }
//    }
//}

@Composable
fun MyBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {//adicione un row a este componente
        Badge(
            backgroundColor = Color.Gray.copy(alpha = 0.3f),
            modifier = Modifier
                .height(20.dp)
                .padding(end = 10.dp)
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun MyDivider(text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Divider(modifier = Modifier.width(100.dp))
        Text(text = text, color = Color.Gray, modifier = Modifier.padding(horizontal = 10.dp))
        Divider(modifier = Modifier.width(100.dp))
    }

}

@Composable
fun MyArticlesCategories(
    imagePath: String,
    subtitle: String,
    onArticleClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val padding = 200.dp
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)))//RoundedCornerShape(5.dp))
            .clickable { onArticleClicked() }
    ) {
        MyImageLoader(
            imagePath = imagePath,
            modifier = Modifier,
            imageModifier = Modifier.size(width = 150.dp, height = padding)
        )
        /*Text(
            text = subtitle,
            maxLines = 1,
            color = Color.Black.copy(alpha = 0.7f),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.offset(x = 10.dp, y = padding - 30.dp)
        )*/
        MyBadge(
            text = subtitle,
            modifier = Modifier.offset(x = 10.dp, y = padding - 30.dp)
        )
    }
}

@Composable
fun MyArticlesCategoriesHeader(
    subtitle: String,
    modifier: Modifier = Modifier,
    onCategoryClicked: () -> Unit
) {
    Row(modifier = modifier) {
        Text(
            text = subtitle,
            maxLines = 1,
            color = Color.Gray,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.weight(weight = 1f)
        )
        Text(
            text = "Ver más",
            maxLines = 1,
            color = Color.Gray.copy(alpha = 0.9f),
            modifier = Modifier.clickable { onCategoryClicked() }//weight(weight = 1f)
        )
    }
}

@Composable
fun MyImageHeader(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(ratio = 1.8f / 1.5f)//SIZE OF THE IMAGE. before was 1.8/1.0
        )
    }
}

@Composable
fun MyScreenTitle(
    title: String,
    subTitle: String,
    letter: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    )
    {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Light),
                maxLines = 1
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(width = 40.dp, height = 40.dp)
                .background(color = MaterialTheme.colors.secondary)
                .clickable { onClick() }
        ) {
            Text(
                text = letter.uppercase(),
                style = MaterialTheme.typography.h6.copy(color = Color.White)
            )
        }
    }
}

@Composable
fun MyFab(
    isQuestion: Boolean = false,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,//
        backgroundColor = MaterialTheme.colors.secondaryVariant,
    ) {
        Icon(imageVector = if(isQuestion) ImageVector.vectorResource(id = R.drawable.ic_circle_question_solid) else Icons.Default.Add, contentDescription = null)
    }
}

@Composable
fun MyWelcomeMessage(
    userName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Bienvenida, $userName",
            style = MaterialTheme.typography.h5,
            maxLines = 2
        )
    }
}

@Composable
fun MyLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.mipmap.logo_transparent),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(170.dp, 110.dp)
    )
}

@Composable
fun MyForgivenPassword(
    text: String,
    modifier: Modifier = Modifier,
    onRegisterClick: (Int) -> Unit = {}
    ) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.fillMaxWidth()
    ) {
//        ClickableText(
//            text = AnnotatedString(
//                text = "Olvidé mi contraseña",
//                spanStyle = SpanStyle(
//                    color = Color.DarkGray,
//                    textDecoration = TextDecoration.Underline
//                )
//            ),
//            onClick = {}
//        )

        ClickableText(
            text = AnnotatedString(
                text = text,
                spanStyle = SpanStyle(
                    color = Color.DarkGray,
                    textDecoration = TextDecoration.Underline
                )
            ),
            onClick = onRegisterClick
        )
    }
}

@Composable
fun MyText(
    text: String,
    isTitle: Boolean = false,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = if (!isTitle) MaterialTheme.typography.subtitle1.copy(//show login text
            fontWeight = FontWeight.Light,
            //color = Color.Gray
        )
        else MaterialTheme.typography.h6.copy(
//show article text
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
        ),
        maxLines = 3,
        modifier = modifier
    )
}

@ExperimentalUnitApi
@Composable
fun MyArticleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,//was body2
        lineHeight = TextUnit(value = 1.5f, type = TextUnitType.Em),
        textAlign = TextAlign.Justify,
        modifier = modifier
    )
}

//Recipe stuffs
@Composable
fun MyRecipeHeader(
    cookingTime: String = "",
    difficulty: String,
    quantity: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        MyText(text = cookingTime)
        MyText(text = quantity)
        MyText(text = difficulty)
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MyRecipeBody(
    title: String = "",
    list: List<String>? = null,
    text: String = "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MyText(text = title, isTitle = true)
        Spacer(modifier = Modifier.height(10.dp))
        if (list == null) {
            MyArticleText(text = text)
        } else {
            list.forEach { item ->
                MyArticleText(text = "- $item")
            }
        }
    }

}

@Composable
fun MySwitchOption(
    text: String,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        MyText(
            text = text,
            isTitle = true,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun MyRecipeTitle(
    title: String = "",
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h5,
        modifier = modifier
    )
}

@Composable
fun MyImageLoader(
    imagePath: String,
    modifier: Modifier,
    imageModifier: Modifier
){
    Box(modifier = modifier){
        val painter = rememberAsyncImagePainter(model = imagePath)
        if (painter.state is AsyncImagePainter.State.Loading)
            CircularProgressIndicator(color = MaterialTheme.colors.secondaryVariant, modifier = Modifier.align(Alignment.Center))
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}