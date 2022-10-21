package com.example.firstexampleapp.ui.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.ViewModelStoreOwner
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
import com.example.firstexampleapp.ui.screen.login.*
import com.example.firstexampleapp.ui.screen.module.ModuleScreen
import com.example.firstexampleapp.ui.screen.module.food.FoodScreen
import com.example.firstexampleapp.ui.screen.module.question.QuestionScreen
import com.example.firstexampleapp.ui.screen.module.recipe.RecipeDetailScreen
import com.example.firstexampleapp.ui.screen.module.recipe.RecipeScreen
import com.example.firstexampleapp.ui.screen.module.task.TaskScreen
import com.example.firstexampleapp.ui.screen.module.track.TrackScreen
import com.example.firstexampleapp.ui.screen.module.weight.WeightScreen
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel
import com.example.firstexampleapp.ui.viewModel.foodViewModel.FoodViewModel
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import com.example.firstexampleapp.ui.viewModel.weightViewModel.WeightViewModel

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
    isDark: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginGraph.route//change to logingraph later
    ) {

        loginGraph(
            navController = navController,
            userViewModel = userViewModel,
            onThemeChange = onThemeChange,
            isDark = isDark
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
                    type = NavType.IntType
                }
            )
        ) {
            ArticleScreen(
                articleState = articleViewModel.getArticle(idArticle = it.arguments?.getInt("idArticle")!!)
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

//modularize login flow
@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    onThemeChange: (Boolean) -> Unit,
    isDark: Boolean
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

        composable(route = Screen.LogoutScren.route) {
            LogoutScreen(
                userViewModel = userViewModel,
                isDark = isDark,
                onThemeChange = onThemeChange,
                onLogoutClicked = {
                    navController.popBackStack()
                    navController.navigate(route = Screen.LoginGraph.route)
                }
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
                userViewModel = userViewModel
            )
        }
        composable(route = Screen.QuestionScreen.route) {
            QuestionScreen(
                questionViewModel = questionViewModel,
            )
        }
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(
                recipeViewModel = recipeViewModel,
                onCardClicked = { idRecipe -> navController.navigate(route = Screen.RecipeDetailScreen.route + idRecipe) }
            )
        }
        composable(
            route = Screen.RecipeDetailScreen.route + "{idRecipe}",
            arguments = listOf(
                navArgument(name = "idRecipe") {
                    type = NavType.IntType
                }
            )
        ) {
            RecipeDetailScreen(
                recipeViewModel = recipeViewModel,
                recipeState = recipeViewModel.getRecipeById(idRecipe = it.arguments?.getInt("idRecipe")!!).collectAsState()
            )
        }
        composable(route = Screen.FoodScreen.route){
            FoodScreen(
                foodViewModel = foodViewModel
            )
        }
        composable(route = Screen.TaskScreen.route){
            TaskScreen(
                taskViewModel = taskViewModel
            )
        }
        composable(route = Screen.TrackScreen.route){
            TrackScreen(
                taskViewModel = taskViewModel,
                recipeViewModel = recipeViewModel,
                questionViewModel = questionViewModel,
                userState = userViewModel.user.collectAsState()
            )
        }
    }
}

//function to navigate between bottom bar navigation items
fun onItemClicked(screen: String, navController: NavController) {
    if (screen == (navController.currentDestination?.route ?: screen)) return
    navController.navigate(screen)
}