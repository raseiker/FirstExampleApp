package com.example.firstexampleapp.ui.screen.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalUnitApi
@Composable
fun ArticleScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        ArticleScreen()
    }
}

@ExperimentalUnitApi
@Composable
fun ArticleScreen() {
    Scaffold(
        floatingActionButton = {
            MyFab()
        },
        topBar = {
            MyTopApBar(
                title = "",
                navIcon = Icons.Default.ArrowBack,
                isFavorite = true,
                actionIconBookMark = ImageVector.vectorResource(id = R.drawable.ic_baseline_bookmark_24),
                actionIcon = Icons.Default.Info
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show image header
            MyImageHeader(
                image = R.mipmap.wiegth,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            //show two badge alignment horizontally
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 20.dp),
            ) {
                MyBadge(text = "ART??CULO")
                MyBadge(text = "PESCADOS Y MARISCOS")
            }

            //show article title
            MyText(
                text = "Beneficios de incluir pescados y mariscos en mi dieta durante el embarazo",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show article body
            MyArticleText(
                text = "Los ??cidos grasos omega-3 son un tipo de grasa presente de forma natural en muchos tipos de pescado. " +
                        "Estos ??cidos tienen un importante efecto protector ante las enfermedades del coraz??n y en el desarrollo" +
                        " del cerebro, el sistema nervioso y la visi??n en el beb??, tanto antes como despu??s de su nacimiento. " +
                        "Para beneficiarte al m??ximo de su potencial, deber??as tomar por lo menos dos raciones de pescado o " +
                        "marisco por semana, tanto durante el embarazo como durante la lactancia. " +
                        "\n\nA pesar de los beneficios de " +
                        "tomar pescado durante el embarazo, debes tener en cuenta que ciertos tipos de pescado pueden contener " +
                        "una mayor concentraci??n de mercurio, un metal que ha sido relacionado con la aparici??n de defectos de " +
                        "nacimiento. Para limitar tu exposici??n al mercurio y poder disfrutar del aporte nutricional del pescado " +
                        "con seguridad, sigue la siguiente recomendaci??n: " +
                        "\n\nNo recomendables durante gestaci??n y lactancia " +
                        "(contenido demasiado alto de mercurio): pez espada, caballa, tibur??n, mero, blanquillo y el at??n fresco " +
                        "o en sushi." +
                        "\n\nFuentes:" +
                        "\nMINSA; Instituto Nacional de Salud",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

        }
    }
}