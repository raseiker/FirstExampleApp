package com.example.firstexampleapp.model.article

data class ArticleState(
    val idArticle: Int = 0,
    val title: String = "",
    val body: String = "",
    val bookmarked: Boolean = false,
    val image: Int = 0,
    val category: String = ArticleCat.None.category
)

sealed class ArticleCat(val category: String) {
    object DrinkandFood : ArticleCat(category = "Bebidas y Comidas")
    object VitaminandMineral : ArticleCat(category = "Vitaminas y Minerales")
    object RecomendedFoods : ArticleCat(category = "Alimentos aconsejados")
    object None: ArticleCat(category = "")
}
