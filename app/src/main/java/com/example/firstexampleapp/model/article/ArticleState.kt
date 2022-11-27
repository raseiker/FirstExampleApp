package com.example.firstexampleapp.model.article

data class ArticleState(
    val idArticle: String = "",
    val title: String = "",
    val body: String = "",
//    val bookmarked: Boolean = false,
    val imagePath: String = "",
    val category: String = ArticleCat.None.category
)

sealed class ArticleCat(val category: String) {
    object DrinkandFood : ArticleCat(category = "Bebidas y Comidas")
    object VitaminandMineral : ArticleCat(category = "Vitaminas y Minerales")
    object RecomendedFoods : ArticleCat(category = "Recomendaciones")
    object None: ArticleCat(category = "")
}
