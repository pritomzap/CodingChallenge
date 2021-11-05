package com.platinacode.challenge.view.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.google.accompanist.pager.*
import com.platinacode.challenge.core.constants.KEY_SELECTED_NOBILE_HOLDER
import com.platinacode.challenge.data.models.noblePrizeResponse.LaureatesItem
import com.platinacode.challenge.data.models.noblePrizeResponse.NobelPrizesItem
import com.platinacode.challenge.ui.theme.PlatinaCodingChallengeTheme
import com.platinacode.challenge.view.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class NobleDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private var leureatesItem:LaureatesItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundles  = intent.extras
        if (bundles != null){
            leureatesItem = intent.getSerializableExtra(KEY_SELECTED_NOBILE_HOLDER) as LaureatesItem
            viewModel.setSelectedItem(leureatesItem)
        }
        setContent {
            PlatinaCodingChallengeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NobleDetails(viewModel.getSelectedItem())
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun NobleDetails(liveData: LiveData<LaureatesItem>){
    val nobleItem: LaureatesItem? by liveData.observeAsState()
    val currentActivity = LocalContext.current as? AppCompatActivity
    nobleItem?.let { item->
        Column{
            TopAppBar(
                title = {
                    Text(text = "Details",style = TextStyle(
                        fontFamily = FontFamily.Default, fontSize = 16.sp,
                        fontWeight = FontWeight.Bold, color = Color.White,
                    ))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        currentActivity?.finish()
                    }) {
                        Icon(Icons.Filled.ArrowBack,"",tint = Color.White)
                    }
                },
                backgroundColor = Color.Black
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Full name: "+item.fullName?.en,style = TextStyle(
                    fontFamily = FontFamily.Default, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                )
                Text(text = "Gender: "+item.gender,modifier = Modifier.padding(top = 2.dp),style = TextStyle(
                    fontFamily = FontFamily.Default, fontSize = 16.sp,
                    fontWeight = FontWeight.Light, color = Color.Gray,
                )
                )
                Text(text = "Birth date: "+item.birth?.date,modifier = Modifier.padding(top = 2.dp),style = TextStyle(
                    fontFamily = FontFamily.Default, fontSize = 16.sp,
                    fontWeight = FontWeight.Light, color = Color.Gray,
                )
                )
                Text(text = "Birth place: "+item.birth?.place?.city?.en+", "+item.birth?.place?.country?.en,modifier = Modifier.padding(top = 2.dp),style = TextStyle(
                    fontFamily = FontFamily.Default, fontSize = 16.sp,
                    fontWeight = FontWeight.Light, color = Color.Gray,
                )
                )
                CreateTab(nobleItem!!)
            }

        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun CreateTab(nobleItem: LaureatesItem) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val listOFTabTitle = mutableListOf<String>()
    val scope = rememberCoroutineScope()
    listOFTabTitle.add("Prizes")
    listOFTabTitle.add("Others")

    val pagerState = rememberPagerState(initialPage = 0)
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 16.dp)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.White,
            divider = {
                TabRowDefaults.Divider(
                    thickness = 2.dp,
                )
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 2.dp,
                    color = Color.White
                )
            }
        ) {
            listOFTabTitle.forEachIndexed { index, _->
                Tab(
                    text = {
                        Text(
                            listOFTabTitle[index],
                            color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        TabsContent(pagerState = pagerState, item = nobleItem)
    }


}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState,item:LaureatesItem) {
    HorizontalPager(state = pagerState,count = 2) { page ->
        when(page) {
            0 -> PrizesScreen(item.nobelPrizes)
            1 -> OtherScreen(item)
        }
    }
}

@Composable
fun OtherScreen(item:LaureatesItem) {
    Column {
        if (item.death != null) DeathView(item)

    }
}

@Composable
private fun DeathView(item: LaureatesItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "Death",
            style = TextStyle(
                fontFamily = FontFamily.Default, fontSize = 16.sp,
                fontWeight = FontWeight.Bold, color = Color.DarkGray,
            ),
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
        )
        Text(
            text = "Date: " + item.death?.date,
            style = TextStyle(
                fontFamily = FontFamily.Default, fontSize = 14.sp,
                fontWeight = FontWeight.Medium, color = Color.LightGray,
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Place: " + item.death?.place?.city?.en + "," + item.death?.place?.country?.en,
            style = TextStyle(
                fontFamily = FontFamily.Default, fontSize = 14.sp,
                fontWeight = FontWeight.Medium, color = Color.LightGray,
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}


@Composable
fun PrizesScreen(nobelPrizes: List<NobelPrizesItem>){
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){
        itemsIndexed(nobelPrizes){ _,eachItem ->
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth()
            ){Divider(
                color = Color.Red,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
            )
                Column(
                    Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Category"+eachItem.categoryFullName?.en,
                        style = TextStyle(
                            fontFamily = FontFamily.Default, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold, color = Color.DarkGray,
                        ),
                    )
                    Text(
                        text = "Award year : ${ eachItem.awardYear }",
                        style = TextStyle(
                            fontFamily = FontFamily.Default, fontSize = 14.sp,
                            fontWeight = FontWeight.Light, color = Color.DarkGray,
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Motivations: ${ eachItem.motivation?.en}",
                        style = TextStyle(
                            fontFamily = FontFamily.Default, fontSize = 14.sp,
                            fontWeight = FontWeight.Light, color = Color.DarkGray
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

            }
        }
    }
}

