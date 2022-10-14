package com.example.firstexampleapp.ui.screen.module

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.navigation.Screen
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyBottomBar
import com.example.firstexampleapp.ui.utils.MyScreenTitle
import com.example.firstexampleapp.ui.utils.MyToolCard
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun ModuleScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        ModuleScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun ModuleScreen(
    userViewModel: UserViewModel,
    onItemBottomBarClicked: (String) -> Unit,
    onCardClicked: (Screen) -> Unit
) {
    Scaffold(
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(false, false, true),
                onItemBottomBarClicked = onItemBottomBarClicked
            )
        }
    ) {

        //Constraint layout
        ConstraintLayout(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {
            val (item1, item2, item3, item4, item5, item6) = createRefs()

            //show screen title
            MyScreenTitle(
                title = "Módulos",
                subTitle = "Herramientas útiles para tu embarazo",
                letter = userViewModel.getFirstNameLetter(),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)
            )

            //show a tool module card 1
            MyToolCard(
                title = "Mi Peso",
                subTitle = "Monitorea tu peso semanalmente",
                image = R.mipmap.vector_weight,
                onClick = { onCardClicked(Screen.WeightScreen) },
                modifier = Modifier.constrainAs(item1) {
                    top.linkTo(parent.top, margin = 160.dp)
                    start.linkTo(parent.start, margin = 25.dp)
                    end.linkTo(item2.start, margin = 10.dp)
                    //bottom.linkTo(parent.bottom, margin = 200.dp)
                }
            )

            //show a tool module card 2
            MyToolCard(
                title = "Preguntas",
                subTitle = "Qué preguntar a tu doctor",
                image = R.mipmap.vector_questions,
                onClick = { onCardClicked(Screen.QuestionScreen) },
                modifier = Modifier.constrainAs(item2) {
                    top.linkTo(parent.top, margin = 120.dp)
                    start.linkTo(item1.end, margin = 10.dp)
                    end.linkTo(parent.end, margin = 25.dp)
                }
            )

            //show a tool module card 3
            MyToolCard(
                title = "Recetas",
                subTitle = "Elabora tus recetas saludables",
                image = R.mipmap.vector_recipe,
                onClick = { onCardClicked(Screen.RecipeScreen) },
                modifier = Modifier.constrainAs(item3) {
                    top.linkTo(item1.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 25.dp)
                    end.linkTo(item4.start, margin = 10.dp)
                    //bottom.linkTo(item5.top, margin = 10.dp)
                }
            )

            //show a tool module card 4
            MyToolCard(
                title = "Calculadora de Alimentos",
                subTitle = "Identifica las calorías exactas",
                image = R.mipmap.vector_nutricalculator_two,
                onClick = { onCardClicked(Screen.FoodScreen) },
                modifier = Modifier.constrainAs(item4) {
                    top.linkTo(item2.bottom, margin = 20.dp)
                    start.linkTo(item3.end, margin = 10.dp)
                    end.linkTo(parent.end, margin = 25.dp)
                }
            )

            //show a tool module card 5
            MyToolCard(
                title = "Seguimiento",
                subTitle = "Monitorea el cumplimiento de tus objetivos",
                image = R.mipmap.vector_recommendations,
                onClick = { onCardClicked(Screen.TracingScreen) },
                modifier = Modifier.constrainAs(item5) {
                    top.linkTo(item3.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 25.dp)
                    end.linkTo(item6.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 25.dp)
                }
            )

            //show a tool module card 6
            MyToolCard(
                title = "Tareas",
                subTitle = "Monitorea tus tareas pendientes",
                image = R.mipmap.vector_task,
                onClick = { onCardClicked(Screen.TaskScreen) },
                modifier = Modifier.constrainAs(item6) {
                    top.linkTo(item4.bottom, margin = 20.dp)
                    start.linkTo(item5.end, margin = 10.dp)
                    end.linkTo(parent.end, margin = 25.dp)
                }
            )
        }

    }
}