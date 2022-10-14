package com.example.firstexampleapp.ui.navigation

sealed class Screen(
    val route: String
){
    object LoginScreen: Screen(route = "login")
    object AboutYouScreen: Screen(route = "about_you")
    object CustomContentScreen: Screen(route = "custom_content")
    object CredentialScreen: Screen(route = "credential")
    object TaskScreen: Screen(route = "task")
    object FoodScreen: Screen(route = "food")
    object WeightScreen: Screen(route = "weight")
    object RecipeScreen: Screen(route = "recipe")
    object QuestionScreen: Screen(route = "question")
    object TracingScreen: Screen(route = "tracing")
    object ModuleScreen: Screen(route = "module")
    object ExploreScreen: Screen(route = "explore")
    object HomeScreen: Screen(route = "home")
    object ArticleListScreen: Screen(route = "article_list/{category}")
    object ArticleScreen: Screen(route = "article/")
    object LoginGraph: Screen(route = "loginGraph")
    object ModuleGraph: Screen(route = "moduleGraph")

}
