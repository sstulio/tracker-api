package tracker

class Transition {

    Date transitionDate
    Location beforeLocation
    Location afterLocation
    Tool tool
    Worker worker

    static constraints = {
        transitionDate (nullable: false)
        beforeLocation (nullable: false)
        afterLocation (nullable: false)
        tool (nullable: true)
        worker (nullable: true)
    }

    static mapping = {
        sort transitionDate: "desc"
    }
}
