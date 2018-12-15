package tracker

class Category {

    String name

    static constraints = {
        name (blank: false)
    }

    static hasMany = [tools: Tool]
}
