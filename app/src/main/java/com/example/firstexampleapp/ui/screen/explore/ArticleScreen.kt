package com.example.firstexampleapp.ui.screen.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.article.ArticleState
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalUnitApi
//@Composable
//fun ArticleScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        ArticleScreen()
//    }
//}

@ExperimentalUnitApi
@Composable
fun ArticleScreen(
    articleState: ArticleState,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit,
    onFABClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            MyFab(
                isQuestion = true,
                onClick = onFABClicked
            )
        },
        topBar = {
            MyTopApBar(
                title = "",
                navIcon = Icons.Default.ArrowBack,
//                isFavorite = true,
//                actionIconBookMark = ImageVector.vectorResource(id = R.drawable.ic_baseline_bookmark_24),
                actionIcon = Icons.Default.Info,
                onNavigateBack = onNavigateBack,
                onInfoClicked = onInfoClicked
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show image header
            MyImageLoader(
                imagePath = articleState.imagePath,
                modifier = Modifier.padding(bottom = 15.dp),
                imageModifier = Modifier.aspectRatio(ratio = 1.8f / 1.5f)
            )

            //show two badge alignment horizontally
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 20.dp),
            ) {
                MyBadge(text = "ARTÍCULO")
                MyBadge(text = articleState.category.uppercase())
            }

            //show article title
            MyText(
                text = articleState.title,
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show article body
            MyArticleText(
                text = articleState.body.replace("LINEBREAK", "\n"),//print a line break
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

        }
    }
}