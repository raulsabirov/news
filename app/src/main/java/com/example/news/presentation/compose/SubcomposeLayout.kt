package com.example.news.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

val profileShort = Profile(
    photo = "Some photo",
    name = "Some name",
    birthday = "Some birthday",
    location = "Some location"
)

val profileFull = Profile(
    photo = "Some photo",
    name = "Some name",
    birthday = "Some birthday",
    location = "Some location",
    email = "Some email",
    phone = "Some phone",
    website = "Some website",
    job = "Some job",
    company = "Some company",
)

val profile = profileShort

@Composable
fun  SubcomposeLayout() {
    Box(modifier = Modifier.width(150.dp).height(270.dp).background(Color.LightGray)) {
        SubcomposeLayout { constraints ->
            val placeableInfo = subcompose("info") {
                ProfileInfo(profile)
            }.first().measure(constraints)
            val infoHeight = placeableInfo.height

            var placeableAvatar: Placeable? = null
            if (infoHeight < 180) {
                placeableAvatar = subcompose("avatar") {
                    ProfileAvatar(profile)
                }.first().measure(constraints)
            }
            val avatarHeight = placeableAvatar?.height ?: 0

            layout(constraints.maxWidth, infoHeight + avatarHeight) {
                placeableAvatar?.placeRelative(0, 0)
                placeableInfo.placeRelative(0, avatarHeight)
            }
        }
    }

}

@Composable
@Preview
fun SubcomposeLayoutPreview(){
    SubcomposeLayout()
}





@Composable
fun ProfileInfo(profile: Profile) {
    Column {
        ProfileInfoItem("Name", profile.name)
        ProfileInfoItem("Birthday", profile.birthday)
        ProfileInfoItem("Location", profile.location)
        ProfileInfoItem("Email", profile.email)
        ProfileInfoItem("Phone", profile.phone)
        ProfileInfoItem("Website", profile.website)
        ProfileInfoItem("Job", profile.job)
        ProfileInfoItem("Company", profile.company)
    }
}

@Composable
fun ProfileInfoItem(label: String, text: String?) {
    text?.let {
        Text(text = label, fontSize = 10.sp)
        Text(text = it, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun ProfileAvatar(profile: Profile) {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}





data class Profile(
    val photo: String,
    val name: String,
    val birthday: String,
    val location: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val website: String? = null,
    val job: String? = null,
    val company: String? = null,
)