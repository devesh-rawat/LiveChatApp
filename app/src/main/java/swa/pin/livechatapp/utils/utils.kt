package swa.pin.livechatapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import swa.pin.livechatapp.routes.DestinationRoutes
import swa.pin.livechatapp.viewmodel.LCViewmodel

fun navigateTo(navController: NavHostController, route: String) {
    navController.navigate(route){
        popUpTo(0)
        launchSingleTop=true
    }

}

@Composable
fun CommonProgressBar() {
    Row(
        modifier = Modifier
            .alpha(.5f)
            .background(Color.LightGray)
            .clickable(enabled = false){}
                .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center)
    {
        CircularProgressIndicator()
    }

}
@Composable
fun checkSignedIn(vm: LCViewmodel,navController: NavHostController){
         val alreadySignedIn= remember { mutableStateOf(false) }
         val signIn=vm.signIn.value
         if(signIn && !alreadySignedIn.value){
             alreadySignedIn.value=true
             navigateTo(navController, DestinationRoutes.ChatList.route)
         }
}