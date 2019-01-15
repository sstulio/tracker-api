package tracker

class Transition {

    Date transitionDate
    Location beforeLocation
    Location afterLocation
    Tool tool

    static constraints = {
        transitionDate (nullable: false);
        beforeLocation (nullable: false);
        afterLocation (nullable: false);
        tool (nullable: false);
    }
}
