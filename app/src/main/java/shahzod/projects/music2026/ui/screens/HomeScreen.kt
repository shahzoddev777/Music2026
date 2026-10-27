package shahzod.projects.music2026.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MusicPlayerScreen(
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onShuffleClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onPlayPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onRepeatClick: () -> Unit = {},
    onLyricsClick: () -> Unit = {}
) {
    // Orqa fon uchun gradient (rasmdagi binafsha ranglar)
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF381458),
            Color(0xFF26103C),
            Color(0xFF140822)
        )
    )

    // Slayder holati uchun state (masalan, 1:24 va 3:56 oralig'i)
    var sliderPosition by remember { mutableStateOf(0.35f) }
    var isPlaying by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Yuqori qism (Header): Orqaga, Sarlavha, Sevimli
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GlassCircularButton(
                    icon = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Orqaga",
                    onClick = onBackClick
                )

                Text(
                    text = "Ethereal Energy",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                GlassCircularButton(
                    icon = Icons.Default.FavoriteBorder,
                    contentDescription = "Sevimlilarga qo'shish",
                    onClick = onFavoriteClick
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Albom rasmi va "Now Playing" yozuvi
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                // Bu yerga o'zingizning rasm resursingizni qo'ying: painterResource(id = R.drawable.your_album)
                // Hozircha namunaviy rang yoki placeholder:
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                ) {
                    // Agar rasm qo'shmoqchi bo'lsangiz quyidagini yoqing:
                    /*
                    Image(
                        painter = painterResource(id = R.drawable.album_art),
                        contentDescription = "Album Art",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    */
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // "Now Playing" indikatori
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Equalizer,
                    contentDescription = null,
                    tint = Color(0xFFAFAFAF),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Now Playing",
                    color = Color(0xFFAFAFAF),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Qo'shiq nomi, muallif va qo'shiq matni (Lyrics) ikonchasi[cite: 3]
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Electric Dream Soul",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Zaro • 3/5",
                        color = Color(0xFFAFAFAF),
                        fontSize = 14.sp
                    )
                }

                IconButton(onClick = onLyricsClick) {
                    Icon(
                        imageVector = Icons.Default.Description, // Yoki mikrofon/matn ikonchasi
                        contentDescription = "Lyrics",
                        tint = Color(0xFFAFAFAF),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Progress Slayder (Vaqt chizig'i)[cite: 3]
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF6B8AFF),
                    activeTrackColor = Color(0xFF6B8AFF),
                    inactiveTrackColor = Color.White.copy(alpha = 0.2f)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Vaqt ko'rsatkichlari[cite: 3]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1:24", color = Color(0xFFAFAFAF), fontSize = 12.sp)
                Text(text = "3:56", color = Color(0xFFAFAFAF), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            // 5. Boshqaruv tugmalari (Shuffle, Previous, Play/Pause, Next, Repeat)[cite: 3]
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Shuffle (Aralashtirish)
                IconButton(onClick = onShuffleClick) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = "Shuffle",
                        tint = Color(0xFFAFAFAF),
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Oldingi qo'shiq
                GlassCircularButton(
                    icon = Icons.Default.SkipPrevious,
                    contentDescription = "Previous",
                    onClick = onPreviousClick,
                    size = 56.dp
                )

                // Play / Pause (Katta ko'k/binafsha tugma)[cite: 3]
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = CircleShape,
                    color = Color(0xFF536DFE), // Asosiy Play tugmasi rangi
                    onClick = {
                        isPlaying = !isPlaying
                        onPlayPauseClick()
                    }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                // Keyingi qo'shiq
                GlassCircularButton(
                    icon = Icons.Default.SkipNext,
                    contentDescription = "Next",
                    onClick = onNextClick,
                    size = 56.dp
                )

                // Repeat (Takrorlash)
                IconButton(onClick = onRepeatClick) {
                    Icon(
                        imageVector = Icons.Default.Repeat,
                        contentDescription = "Repeat",
                        tint = Color(0xFFAFAFAF),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Yordamchi Glassmorphism (Shaffof) tugma komponenti
@Composable
fun GlassCircularButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    size: androidx.compose.ui.unit.Dp = 48.dp
) {
    Surface(
        modifier = Modifier.size(size),
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.25f)),
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.White,
                modifier = Modifier.size(size * 0.5f)
            )
        }
    }
}