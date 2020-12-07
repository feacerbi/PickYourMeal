package com.felipeacerbi.pickyourmeal.viewstate

interface ViewStateReducer<VS : ViewState> {
    val reduce: (VS) -> VS
}