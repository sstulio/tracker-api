package tracker

class Tool {

    String name
    String code
    String image

    Category category
    Location location

    static constraints = {
        name (blank: false)
        code (blank: false, unique: true)
        image (nullable: true)
        category (nullable: true)
        location (nullable: false)
    }

    static mapping = {
        location lazy: false
        category lazy: false
    }

    static hasMany = [transitions: Transition]
}
