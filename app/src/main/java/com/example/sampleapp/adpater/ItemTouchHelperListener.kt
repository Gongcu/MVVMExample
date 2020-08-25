package com.example.sampleapp.adpater

interface ItemTouchHelperListener {
    fun onItemMove(fromPos:Int, targetPos:Int)
    fun onItemDismiss(pos:Int)
}