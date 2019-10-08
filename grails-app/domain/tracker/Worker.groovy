package tracker

class Worker {

    String name
    String code
    String image

    Location location

    static constraints = {
        name (blank: false)
        code (blank: false, unique: true)
        image (nullable: true)
        location (nullable: false)
    }

    static mapping = {
        location lazy: false
    }

    static hasMany = [transitions: Transition]
}
