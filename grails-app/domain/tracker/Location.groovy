package tracker

class Location {

    String name
    String image

    static constraints = {
        name (blank: false)
        image (nullable: true)
    }

    static hasMany = [tools: Tool]
}
