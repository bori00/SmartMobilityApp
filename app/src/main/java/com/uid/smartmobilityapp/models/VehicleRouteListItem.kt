package com.uid.smartmobilityapp.models

class VehicleRouteListItem {
    constructor(locationOne: String,locationTwo : String, departureTime: String, clockImage: Int, middleImage: Int, hour: String) {
        this.locationOne = locationOne
        this.locationTwo = locationTwo
        this.clockImage = clockImage
        this.middleImage = middleImage
        this.hour = hour
        this.departureTime = departureTime
    }

    var locationOne: String
    var locationTwo: String
    var clockImage: Int
    var middleImage: Int
    var hour: String
    var departureTime: String
}