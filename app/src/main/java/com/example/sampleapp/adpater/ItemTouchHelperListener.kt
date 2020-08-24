package com.example.sampleapp.adpater

interface ItemTouchHelperListener {
    fun onItemMove(fromPos:Int, targetPos:Int)
    fun onItemDismiss(pos:Int) //swipe 사용하려면 주석 해제 }
    fun itemMoveFinished()
}