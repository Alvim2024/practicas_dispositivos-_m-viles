package com.example.cursofirebaselite.Presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.Presentation.model.Artist
import com.example.cursofirebaselite.ui.theme.Black
import androidx.compose.runtime.collectAsState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource

import com.example.cursofirebaselite.Presentation.model.Player
import com.example.cursofirebaselite.R

import com.example.cursofirebaselite.ui.theme.Purple40


//@Preview
@Composable
fun HomeScreen(viewmodel: HomeViewmodel = HomeViewmodel()) {
    val artists: State<List<Artist>> = viewmodel.artist.collectAsState()
    val player by viewmodel.player.collectAsState()


    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Text(
            "Popular Artists",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )


        LazyRow {
            items(artists.value) {
                ArtistItem(artist= it, onItemSelected = {viewmodel.addPlayer(it)})
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        player?.let {

            PlayerComponent(player = it,
                onCancelSelected = {viewmodel.onCancelSelected() },
                onPlaySelected = {viewmodel.onPlaySelected()})
        }


    }
}

private fun HomeViewmodel.addPlayer(artist: Artist) {
    val ref = database.reference.child("player")
    val player = Player(artist = artist, play = true)
    ref.setValue(player)
}

@Composable
fun PlayerComponent(player: Player, onPlaySelected: () -> Unit, onCancelSelected: () -> Unit) {
    val icon = if (player.play == true) R.drawable.ic_pause else R.drawable.ic_play

    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Purple40),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = player.artist?.name.orEmpty(),
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Play / pause",
            modifier = Modifier.size(40.dp).clickable{onPlaySelected()}
        )
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close",
            modifier = Modifier.size(40.dp).clickable{onCancelSelected()}
        )

    }
}

private fun HomeViewmodel.onPlaySelected() {
    if (player.value != null) {
        val currentPlayer = player.value?.copy(play = !player.value?.play!!)
        val ref = database.reference.child("player")
        ref.setValue(currentPlayer)
    }
}

private fun HomeViewmodel.onCancelSelected() {
    val ref = database.reference.child("player")
    ref.setValue(null)
}


@Composable
fun ArtistItem(artist: Artist, onItemSelected: (Artist) -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onItemSelected(artist) }) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = artist.image,
            contentDescription = "Artist Image",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(), color = Color.White)
    }
}

@Preview
@Composable
fun ArtistItemPreview() {
    val artist = Artist(
        "pepe",
        "el mejor",
        "https://static.wikia.nocookie.net/warnerbros/images/3/31/Piolin_%2812%29-2-.gif/revision/latest?cb=20120402200947&path-prefix=es",
        //emptyList()
    )
    ArtistItem(artist = artist){}
}


//fun CreateArtist(db: FirebaseFirestore) {
//    val random = (1..100).random()
//    val artist = Artist(
//        name = "Random $random",
//        numberOfSongs = random
//    )
//
//    db.collection("artists")
//        .add(artist)
//        .addOnSuccessListener {
//            Log.i("aris", "Success")
//        }
//        .addOnFailureListener {
//            Log.i("aris", "Failure")
//        }
//        .addOnCompleteListener {
//            Log.i("aris", "Complete")
//        }
//}