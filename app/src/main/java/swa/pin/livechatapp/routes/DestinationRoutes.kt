package swa.pin.livechatapp.routes

sealed class DestinationRoutes(val route:String) {
    object Login : DestinationRoutes("login")
    object SignUp : DestinationRoutes("signUp")
    object ChatList : DestinationRoutes("chatList")
    object SingleChat : DestinationRoutes("singleChat{chatId}"){
        fun createRoute(id: String) = "singleChat/$id"
    }
    object Status : DestinationRoutes("status")
    object SingleStatus : DestinationRoutes("singleStatus{userId}"){
        fun createRoute(userId: String) = "singleStatus/$userId"
    }
    object Profile : DestinationRoutes("profile")
}