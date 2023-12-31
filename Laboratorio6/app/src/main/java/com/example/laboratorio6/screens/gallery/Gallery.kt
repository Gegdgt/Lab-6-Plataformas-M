package com.example.laboratorio6.screens.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.laboratorio6.R
import com.example.laboratorio6.navigation.Laboratorio6Screens
import com.example.laboratorio6.ui.theme.Laboratorio6Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Galleryfun(navController: NavController){
    val pagerState = rememberPagerState(initialPage = 1)
    val sliderList = listOf(
        R.drawable.arte1,
        R.drawable.arte2,
        R.drawable.arte3,
        R.drawable.arte4,
        R.drawable.arte5,
        R.drawable.arte6,
        R.drawable.arte7,
        R.drawable.arte8,
        R.drawable.arte9,
        R.drawable.arte10,
    )
    val infoList = listOf(
        //arte1
        arte(
            "Noche Estrellada",
            "Vincent van Gogh",
            "1889"
        ),
        //arte2
        arte(
            "La Joven De La Perla",
            "Johannes Vermeer",
            "1665"
        ),
        //arte3
        arte(
            "Las Meninas",
            "Diego Velázquez",
            "1656"
        ),
        //arte4
        arte(
            "El Nacimiento De Venus",
            "Sandro Botticelli",
            "1486"
        ),
        //arte5
        arte(
            "El beso",
            "Gustav Klimt",
            "1908"
        ),
        //arte6
        arte(
            "La Gioconda",
            "Leonardo da Vinci",
            "1797"
        ),
        //arte7
        arte(
            "Retrato",
            "Nadar",
            "1439"
        ),
        //arte8
        arte(
            "La ronda de noche",
            "Rembrandt",
            "1642"
        ),
        //arte9
        arte(
            "Achille Emperaire",
            "Paul Cézanne",
            "1868"
        ),
        //arte10
        arte(
            "Triple retrato de Carlos I",
            "Anton van Dyck",
            "1636"
        ),
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                enabled = pagerState.currentPage > 0,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage-1)
                    }
                }) {
                Icon(Icons.Default.ArrowLeft, null)
            }

            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 35.dp),
                modifier = Modifier
                    .height(350.dp)
                    .weight(1f)
            ) { page ->
                Card(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                                .also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sliderList[page])
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.baseline_error_24)
                    )
                }
            }
            IconButton(
                enabled = pagerState.currentPage < pagerState.pageCount-1,
                onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }
            }) {
                Icon(Icons.Default.ArrowRight,null)
            }
        }
        Row (
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
          repeat(sliderList.size){it->
              val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
              Box(modifier = Modifier
                  .padding(2.dp)
                  .clip(CircleShape)
                  .size(20.dp)
                  .background(color)
                  .clickable {
                      scope.launch {
                          pagerState.animateScrollToPage(it)
                      }
                  }
              )
          }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val currentInfo = infoList[pagerState.currentPage]
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = currentInfo.title,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Autor: ${currentInfo.painter}")
                Text("Año: ${currentInfo.year}")
            }
        }
    }
    Button(
        onClick = {
            navController.navigate(Laboratorio6Screens.LoginScreen.name)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Cerrar Sesión")
    }
}

data class arte(
    val title: String,
    val painter: String,
    val year: String
)
