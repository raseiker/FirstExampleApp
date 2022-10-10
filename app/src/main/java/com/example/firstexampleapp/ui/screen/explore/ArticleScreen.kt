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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.model.article.ArticleState
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel

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
    articleState: ArticleState
) {
    Scaffold(
//        floatingActionButton = {
//            MyFab()
//        },
        topBar = {
            MyTopApBar(
                title = "",
                navIcon = Icons.Default.ArrowBack,
//                isFavorite = true,
//                actionIconBookMark = ImageVector.vectorResource(id = R.drawable.ic_baseline_bookmark_24),
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
                image = articleState.image,//R.mipmap.wiegth,
                modifier = Modifier.padding(bottom = 15.dp)
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
                text = stringResource(id = articleState.title.toInt()),
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show article body
            MyArticleText(
                text = stringResource(articleState.body.toInt()),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

        }
    }
}