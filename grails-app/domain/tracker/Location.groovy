package tracker

import org.hibernate.jdbc.Work

class Location {

    String name
    String image
    String costCenter

    static constraints = {
        name (blank: false)
        image (nullable: true)
        costCenter (nullable: true)
    }

    static hasMany = [tools: Tool, workers: Worker]
}
