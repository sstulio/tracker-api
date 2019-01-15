package tracker

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TransitionServiceSpec extends Specification {

    TransitionService transitionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Transition(...).save(flush: true, failOnError: true)
        //new Transition(...).save(flush: true, failOnError: true)
        //Transition transition = new Transition(...).save(flush: true, failOnError: true)
        //new Transition(...).save(flush: true, failOnError: true)
        //new Transition(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //transition.id
    }

    void "test get"() {
        setupData()

        expect:
        transitionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Transition> transitionList = transitionService.list(max: 2, offset: 2)

        then:
        transitionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        transitionService.count() == 5
    }

    void "test delete"() {
        Long transitionId = setupData()

        expect:
        transitionService.count() == 5

        when:
        transitionService.delete(transitionId)
        sessionFactory.currentSession.flush()

        then:
        transitionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Transition transition = new Transition()
        transitionService.save(transition)

        then:
        transition.id != null
    }
}
