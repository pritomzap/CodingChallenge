package com.platinacode.challenge.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.rememberImagePainter
import com.deshipay.common.network.NetworkResult
import com.google.accompanist.pager.ExperimentalPagerApi
import com.platinacode.challenge.core.constants.KEY_SELECTED_NOBILE_HOLDER
import com.platinacode.challenge.data.models.noblePrizeResponse.LaureatesItem
import com.platinacode.challenge.data.models.noblePrizeResponse.ResponseNoblePrize
import com.platinacode.challenge.ui.theme.PlatinaCodingChallengeTheme
import com.platinacode.challenge.view.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NobleListActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @ExperimentalPagerApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatinaCodingChallengeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FilmList(viewModel.getFilms())
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FilmList(liveData: LiveData<NetworkResult<ResponseNoblePrize>>) {
    val openDialog = remember { mutableStateOf(false)  }
    val context = LocalContext.current
    val filmLists:NetworkResult<ResponseNoblePrize>? by liveData.observeAsState()
    filmLists?.let {
        when (it) {
            is NetworkResult.Success -> {
                it.data?.let {
                    it.laureates?.let { it1 -> LiveDataComponentList(it1) }
                }
            }

            is NetworkResult.Error -> {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Please find below errors")
                    },
                    text = {
                        Text(it.message.toString())
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                                (context as AppCompatActivity).finish()
                            }) {
                            Text("Exit")
                        }
                    })
            }

            is NetworkResult.Loading -> {
                LiveDataLoadingComponent()
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LiveDataComponentList(filmList: List<LaureatesItem>) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ){
        stickyHeader {
            Text(
                text = "Noble prizes",
                style = TextStyle(
                    fontFamily = FontFamily.Serif, fontSize = 20.sp,
                    fontWeight = FontWeight.Light, color = Color.DarkGray
                ),
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        itemsIndexed(filmList){ _,eachItem ->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                ) {
                    Row(
                        Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth()
                            .clickable(onClick = {
                                val intent = Intent(context, NobleDetailsActivity::class.java)
                                intent.putExtra(KEY_SELECTED_NOBILE_HOLDER, eachItem)
                                context.startActivity(intent)
                            })
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
                                text = eachItem.fullName?.en?:"N/A",
                                style = TextStyle(
                                    fontFamily = FontFamily.Default, fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold, color = Color.DarkGray,
                                ),
                            )
                            Text(
                                text = "Date of birth: ${ eachItem.birth?.date ?: "N/A" }",
                                style = TextStyle(
                                    fontFamily = FontFamily.Default, fontSize = 14.sp,
                                    fontWeight = FontWeight.Light, color = Color.DarkGray,
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "In ${ eachItem.birth?.place?.city?.en}, ${ eachItem.birth?.place?.country?.en}",
                                style = TextStyle(
                                    fontFamily = FontFamily.Default, fontSize = 14.sp,
                                    fontWeight = FontWeight.Light, color = Color.DarkGray
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Got total ${ eachItem.nobelPrizes?.size} prizes",
                                style = TextStyle(
                                    fontFamily = FontFamily.Default, fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium, color = Color.DarkGray
                                ),
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }

                    }
                }
            }
    }
}

inline fun <reified T : AppCompatActivity> Context.launchActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlatinaCodingChallengeTheme {

    }
}

@Composable
fun LiveDataLoadingComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
    }
}

