package com.example.firstexampleapp.ui.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstexampleapp.model.article.ArticleCat
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.ui.screen.explore.ArticleScreen
import com.example.firstexampleapp.ui.screen.explore.ExploreScreen
import com.example.firstexampleapp.ui.screen.home.HomeScreen
import com.example.firstexampleapp.ui.screen.login.AboutYouScreen
import com.example.firstexampleapp.ui.screen.login.CredentialScreen
import com.example.firstexampleapp.ui.screen.login.CustomContentScreen
import com.example.firstexampleapp.ui.screen.login.LoginScreen
import com.example.firstexampleapp.ui.screen.module.ModuleScreen
import com.example.firstexampleapp.ui.screen.module.question.QuestionScreen
import com.example.firstexampleapp.ui.screen.module.weight.WeightScreen
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import com.example.firstexampleapp.ui.viewModel.weightViewModel.WeightViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalUnitApi::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    weightViewModel: WeightViewModel = viewModel(),
    questionViewModel: QuestionViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginGraph.route//change to logingraph later
    ) {

        loginGraph(
            navController = navController,
            userViewModel = userViewModel
        )

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                articleViewModel = articleViewModel,
                userViewModel = userViewModel,
                onItemBottomBarClicked = {
                    onItemClicked(
                        screen = it,
                        navController = navController
                    )
                },
                onCardActionClicked = { Log.d("cardClick", it) },
                onCardArticleClicked = { idArticle -> navController.navigate(route = Screen.ArticleScreen.route + idArticle)}
            )
        }

        composable(route = Screen.ExploreScreen.route) {
            ExploreScreen(
                articleViewModel = articleViewModel,
                userViewModel = userViewModel,
                onCategoryClicked = { screen -> Log.d("catClicked", screen) },
                onArticleClicked = { idArticle -> navController.navigate(route = Screen.ArticleScreen.route + idArticle)},
                onItemBottomBarClicked = {
                    onItemClicked(
                        screen = it,
                        navController = navController
                    )
                }
            )
        }

        composable(
            route = Screen.ArticleScreen.route + "{idArticle}",
            arguments = listOf(
                navArgument(name = "idArticle"){
                    type = NavType.IntType
                }
            )
        ){
            ArticleScreen(
                articleState = articleViewModel.getArticle(idArticle = it.arguments?.getInt("idArticle")!!)
            )
        }

        moduleGraph(
            navController = navController,
            userViewModel = userViewModel,
            weightViewModel = weightViewModel,
            questionViewModel = questionViewModel
        )


    }
}

//modularize login flow
@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = Screen.LoginGraph.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                userViewModel = userViewModel,
                onSingInClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                },
                onRegisterClicked = { navController.navigate(Screen.AboutYouScreen.route) }
            )
        }

        composable(route = Screen.AboutYouScreen.route) {
            AboutYouScreen(
                userViewModel = userViewModel,
                onNextClicked = { navController.navigate(Screen.CustomContentScreen.route) }
            )
        }

        composable(route = Screen.CustomContentScreen.route) {
            CustomContentScreen(
                userViewModel = userViewModel,
                onNextClicked = { navController.navigate(Screen.CredentialScreen.route) }
            )
        }

        composable(route = Screen.CredentialScreen.route) {
            CredentialScreen(
                userViewModel = userViewModel,
                onDoneClicked = { navController.navigate(Screen.HomeScreen.route) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.moduleGraph(
    navController: NavController,
    userViewModel: UserViewModel,
    weightViewModel: WeightViewModel,
    questionViewModel: QuestionViewModel
){
    navigation(
        startDestination = Screen.ModuleScreen.route,
        route = Screen.ModuleGraph.route
    ){
        composable(route = Screen.ModuleScreen.route) {
            ModuleScreen(
                userViewModel = userViewModel,
                onCardClicked = { navController.navigate(it.route) },
                onItemBottomBarClicked = {
                    onItemClicked(
                        screen = it,
                        navController = navController
                    )
                }
            )
        }

        composable(route = Screen.WeightScreen.route){
            WeightScreen(
                weightViewModel = weightViewModel,
                userViewModel = userViewModel
            )
        }

        composable(route = Screen.QuestionScreen.route){
            QuestionScreen(
                questionViewModel = questionViewModel,
            )
        }
    }
}

//function to navigate between bottom bar navigation items
fun onItemClicked(screen: String, navController: NavController) {
    if (screen == (navController.currentDestination?.route ?: screen)) return
    navController.navigate(screen)
}