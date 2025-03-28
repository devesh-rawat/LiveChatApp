package swa.pin.livechatapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import swa.pin.livechatapp.Screens.ChatListScreen
import swa.pin.livechatapp.Screens.LoginScreen
import swa.pin.livechatapp.Screens.ProfileScreen
import swa.pin.livechatapp.Screens.SignUpScreen
import swa.pin.livechatapp.Screens.SingleChatScreen
import swa.pin.livechatapp.Screens.SingleStatusScreen
import swa.pin.livechatapp.Screens.StatusScreen
import swa.pin.livechatapp.routes.DestinationRoutes
import swa.pin.livechatapp.viewmodel.LCViewmodel

@Composable
fun ChatAppNavigation(modifier: Modifier) {
    val navController= rememberNavController()
    val vm=hiltViewModel<LCViewmodel>()
    NavHost(navController=navController, startDestination = DestinationRoutes.SignUp.route) {

        composable(DestinationRoutes.Login.route) {
            LoginScreen(navController)
        }
        composable(DestinationRoutes.SignUp.route) {
            SignUpScreen(navController,vm)
        }
        composable(DestinationRoutes.SingleChat.route) {
            SingleChatScreen()
        }
        composable(DestinationRoutes.ChatList.route) {
            ChatListScreen()
        }
        composable(DestinationRoutes.Status.route) {
            StatusScreen()
        }
        composable(DestinationRoutes.Profile.route) {
            ProfileScreen()
        }
        composable(DestinationRoutes.SingleStatus.route) {
            SingleStatusScreen()
        }



    }
}