package com.example.tablayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.tablayout.ui.theme.TabLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TabLayoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    TabScreen(
                        TabItems.getTabList(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    tabItems: List<TabItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }

        var pagerState = rememberPagerState {
            tabItems.size
        }

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = tabItem.title) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex == index)
                                tabItem.selectedIcon else tabItem.unSelectedIcon,
                            contentDescription = null,
                        )
                    },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tabItems[selectedTabIndex].title
                )
            }
        }
    }
}

data class TabItem(
    val title: String,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

object TabItems {
    fun getTabList(): List<TabItem> {
        return listOf(
            TabItem(
                title = "Home",
                unSelectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            TabItem(
                title = "Shopping",
                unSelectedIcon = Icons.Outlined.ShoppingCart,
                selectedIcon = Icons.Filled.ShoppingCart
            ),
            TabItem(
                title = "Account",
                unSelectedIcon = Icons.Outlined.AccountBox,
                selectedIcon = Icons.Filled.AccountBox
            ),
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
    TabLayoutTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TabScreen(TabItems.getTabList())
        }
    }
}