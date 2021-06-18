package ar.edu.unlam.intermediamarvelchallenge.utils

import android.view.View

fun View.show(){
    if (this.visibility == View.GONE){
        this.visibility = View.VISIBLE
    }
}

fun View.hide(){
    if (this.visibility == View.VISIBLE){
        this.visibility = View.GONE
    }
}

fun View.isVisible():Boolean{
    return this.visibility == View.VISIBLE
}

