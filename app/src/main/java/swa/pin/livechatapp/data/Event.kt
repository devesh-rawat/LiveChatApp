package swa.pin.livechatapp.data

open class Event<out T>(val content:T){
    var hasBennHandled=false
    fun getContentOrNull():T?{
        return if(hasBennHandled) null
        else {
            hasBennHandled=true
            content
        }
    }



}