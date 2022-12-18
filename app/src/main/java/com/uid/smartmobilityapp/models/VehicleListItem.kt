package com.uid.smartmobilityapp.models

class VehicleListItem {

    constructor(title: String,departureTime: String, description: String, image: Int, ecologic: Boolean, cheap: Boolean, expanded: Boolean) {
        this.title = title
        this.description = description
        this.image = image
        this.isEcologic = ecologic
        this.isCheap = cheap
        this.isExpanded = expanded
        this.departureTime = departureTime
    }

    var title: String
    var description: String
    var image: Int
    var departureTime: String
    var isCheap: Boolean
    var isEcologic: Boolean
    var isExpanded: Boolean

}