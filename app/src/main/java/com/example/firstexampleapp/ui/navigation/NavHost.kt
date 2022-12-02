package com.example.firstexampleapp.ui.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstexampleapp.ui.screen.explore.ArticleScreen
import com.example.firstexampleapp.ui.screen.explore.ExploreScreen
import com.example.firstexampleapp.ui.screen.home.HomeScreen
import com.example.firstexampleapp.ui.screen.login.*
import com.example.firstexampleapp.ui.screen.module.ModuleScreen
import com.example.firstexampleapp.ui.screen.module.food.FoodScreen
import com.example.firstexampleapp.ui.screen.module.question.QuestionScreen
import com.example.firstexampleapp.ui.screen.module.question.InfoScreen
import com.example.firstexampleapp.ui.screen.module.recipe.RecipeDetailScreen
import com.example.firstexampleapp.ui.screen.module.recipe.RecipeScreen
import com.example.firstexampleapp.ui.screen.module.task.TaskScreen
import com.example.firstexampleapp.ui.screen.module.track.TrackScreen
import com.example.firstexampleapp.ui.screen.module.weight.WeightScreen
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel
import com.example.firstexampleapp.ui.viewModel.foodViewModel.FoodViewModel
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import com.example.firstexampleapp.ui.viewModel.weightViewModel.WeightViewModel
import com.example.firstexampleapp.R

@OptIn(ExperimentalMaterialApi::class, ExperimentalUnitApi::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    weightViewModel: WeightViewModel = viewModel(),
    questionViewModel: QuestionViewModel = viewModel(),
    recipeViewModel: RecipeViewModel = viewModel(),
    foodViewModel: FoodViewModel = viewModel(),
    taskViewModel: TaskViewModel = viewModel(),
) {
    FirstExampleAppTheme(darkTheme = userViewModel.isDark.value) {
        NavHost(
            navController = navController,
            startDestination = Screen.LoginGraph.route
        ) {

            loginGraph(
                navController = navController,
                taskViewModel = taskViewModel,
                questionViewModel = questionViewModel,
                recipeViewModel = recipeViewModel,
                userViewModel = userViewModel,
                isDark = userViewModel.isDark.value,
                onThemeChange = { userViewModel.onThemeChange() }
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
                    onCardArticleClicked = { idArticle -> navController.navigate(route = Screen.ArticleScreen.route + idArticle) }
                )
            }

            composable(route = Screen.ExploreScreen.route) {
                ExploreScreen(
                    articleViewModel = articleViewModel,
                    userViewModel = userViewModel,
                    onCategoryClicked = { screen -> Log.d("catClicked", screen) },
                    onArticleClicked = { idArticle -> navController.navigate(route = Screen.ArticleScreen.route + idArticle) },
                    onSignatureClicked = { navController.navigate(Screen.LogoutScren.route) },
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
                    navArgument(name = "idArticle") {
                        type = NavType.StringType//was int
                    }
                )
            ) {
                ArticleScreen(
                    articleState = articleViewModel.getArticleById(idArticle = it.arguments?.getString("idArticle")!!),
                    onNavigateBack = { navController.navigateUp() },
                    onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 0, isDark = userViewModel.isDark.value)) },
                    onFABClicked = { navController.navigate(route = Screen.QuestionScreen.route) }
                )
            }

            moduleGraph(
                navController = navController,
                userViewModel = userViewModel,
                weightViewModel = weightViewModel,
                questionViewModel = questionViewModel,
                recipeViewModel = recipeViewModel,
                foodViewModel = foodViewModel,
                taskViewModel = taskViewModel
            )
        }
    }
}

//modularize login flow
@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    questionViewModel: QuestionViewModel,
    recipeViewModel: RecipeViewModel,
    userViewModel: UserViewModel,
    onThemeChange: (Boolean) -> Unit,
    isDark: Boolean
) {
    navigation(
        startDestination = Screen.SplashScreen.route,//Screen.LoginScreen.route,
        route = Screen.LoginGraph.route
    ) {
        composable(route = Screen.SplashScreen.route){
            SplashScreen(
                userViewModel = userViewModel,
                questionViewModel = questionViewModel,
                taskViewModel = taskViewModel,
                recipeViewModel = recipeViewModel,
                onNavigateTo = { destination -> navController.popBackStack(); navController.navigate(route = destination) }
            )
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                userViewModel = userViewModel,
                questionViewModel = questionViewModel,
                taskViewModel = taskViewModel,
                recipeViewModel = recipeViewModel,
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
                onNextClicked = { navController.navigate(Screen.CustomContentScreen.route) },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(route = Screen.CustomContentScreen.route) {
            CustomContentScreen(
                userViewModel = userViewModel,
                onNextClicked = { navController.navigate(Screen.CredentialScreen.route) },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(route = Screen.CredentialScreen.route) {
            CredentialScreen(
                userViewModel = userViewModel,
                taskViewModel = taskViewModel,
                questionViewModel = questionViewModel,
                recipeViewModel = recipeViewModel,
                onDoneClicked = { navController.navigate(Screen.HomeScreen.route) },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(route = Screen.LogoutScren.route) {
            LogoutScreen(
                userViewModel = userViewModel,
                isDark = isDark,
                onThemeChange = onThemeChange,
                onLogoutClicked = { navController.popBackStack(); navController.navigate(route = Screen.LoginScreen.route) },
                onNavigateBack = { navController.navigateUp() },
                onTermClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 6, isDark = userViewModel.isDark.value)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.moduleGraph(
    navController: NavController,
    userViewModel: UserViewModel,
    weightViewModel: WeightViewModel,
    questionViewModel: QuestionViewModel,
    recipeViewModel: RecipeViewModel,
    foodViewModel: FoodViewModel,
    taskViewModel: TaskViewModel
) {
    navigation(
        startDestination = Screen.ModuleScreen.route,
        route = Screen.ModuleGraph.route
    ) {
        composable(route = Screen.ModuleScreen.route) {
            ModuleScreen(
                userViewModel = userViewModel,
                onCardClicked = { navController.navigate(it.route) },
                onSignatureClicked = { navController.navigate(route = Screen.LogoutScren.route) },
                onItemBottomBarClicked = {
                    onItemClicked(
                        screen = it,
                        navController = navController
                    )
                }
            )
        }
        composable(route = Screen.WeightScreen.route) {
            WeightScreen(
                weightViewModel = weightViewModel,
                userViewModel = userViewModel,
                onNavigateBack = { navController.navigateUp() },
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 3, isDark = userViewModel.isDark.value)) }
            )
        }
        composable(route = Screen.QuestionScreen.route) {
            QuestionScreen(
                questionViewModel = questionViewModel,
                onExpandibleCardClicked = { navController.navigate(Screen.InfoScreen.route) },
                onNavigateBack = { navController.navigateUp() },
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 4, isDark = userViewModel.isDark.value)) }
            )
        }
        composable(
            route = Screen.InfoScreen.route + "{imageId}",
            arguments = listOf(navArgument(name = "imageId"){ type = NavType.IntType })
        ) {
            InfoScreen(
                imageInfo = it.arguments?.getInt("imageId")!!,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(
                recipeViewModel = recipeViewModel,
                onCardClicked = { idRecipe -> navController.navigate(route = Screen.RecipeDetailScreen.route + idRecipe) },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = Screen.RecipeDetailScreen.route + "{idRecipe}",
            arguments = listOf(
                navArgument(name = "idRecipe") {
                    type = NavType.StringType//was Inttype
                }
            )
        ) {
            RecipeDetailScreen(
                recipeViewModel = recipeViewModel,
                recipeState = recipeViewModel.getRecipeById(idRecipe = it.arguments?.getString("idRecipe")!!),
                onNavigateBack = { navController.navigateUp() },
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 5, isDark = userViewModel.isDark.value)) }
            )
        }
        composable(route = Screen.FoodScreen.route){
            FoodScreen(
                foodViewModel = foodViewModel,
                onNavigateBack = {navController.navigateUp()},
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 7, isDark = userViewModel.isDark.value)) }
            )
        }
        composable(route = Screen.TaskScreen.route){
            TaskScreen(
                taskViewModel = taskViewModel,
                onNavigateBack = { navController.navigateUp() },
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 1, isDark = userViewModel.isDark.value)) }
            )
        }
        composable(route = Screen.TrackScreen.route){
            TrackScreen(
                taskCompletedCount = taskViewModel.getTaskCompletedCount(),
                recipeCompletedCount = recipeViewModel.getRecipeCompletedCount(),
                questionCompletedCount = questionViewModel.getQuestionCompletedCount(),
                onNavigateBack = {navController.navigateUp()},
                onInfoClicked = { navController.navigate(route = Screen.InfoScreen.route + getInfo(code = 2, isDark = userViewModel.isDark.value)) },
                userName = userViewModel.user.collectAsState().value.name
            )
        }
    }
}

//function to navigate between bottom bar navigation items
fun onItemClicked(screen: String, navController: NavController) {
    if (screen == (navController.currentDestination?.route ?: screen)) return
    navController.navigate(screen)
}

fun getInfo(code: Int, isDark: Boolean): Int{
    return when (code) {
        0 -> if (!isDark) R.mipmap.article_info else R.mipmap.article_info_dark//article
        1 -> if (!isDark) R.mipmap.task_info else R.mipmap.task_info_dark//article
        2 -> if (!isDark) R.mipmap.track_info else R.mipmap.track_info_dark//article
        3 -> if (!isDark) R.mipmap.weight_info else R.mipmap.weight_info_dark//article
        4 -> if (!isDark) R.mipmap.question_info else R.mipmap.question_info_dark//article
        5 -> if (!isDark) R.mipmap.recipe_info else R.mipmap.recipe_info_dark//article
        6 -> if (!isDark) R.mipmap.terms_info else R.mipmap.terms_info_dark//terms
        7 -> if (!isDark) R.mipmap.food_info else R.mipmap.food_info_dark//terms
        else -> R.mipmap.terms_info
    }
}

/*
Developed by Cesar Moncada -> raseiker1@gmail.com
 */