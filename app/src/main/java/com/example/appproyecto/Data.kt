package com.example.appproyecto

class Data {

    var id:Int = 0
    var icon:String = ""
    var title:String = ""
    var phone:String = ""

    constructor(id:Int, title:String, phone:String, icon:String){
        this.id = id
        this.icon = icon
        this.title = title
        this.phone = phone
    }
}