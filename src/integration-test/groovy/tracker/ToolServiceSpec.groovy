package tracker

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ToolServiceSpec extends Specification {

    ToolService toolService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Tool(...).save(flush: true, failOnError: true)
        //new Tool(...).save(flush: true, failOnError: true)
        //Tool tool = new Tool(...).save(flush: true, failOnError: true)
        //new Tool(...).save(flush: true, failOnError: true)
        //new Tool(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tool.id
    }

    void "test get"() {
        setupData()

        expect:
        toolService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Tool> toolList = toolService.list(max: 2, offset: 2)

        then:
        toolList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        toolService.count() == 5
    }

    void "test delete"() {
        Long toolId = setupData()

        expect:
        toolService.count() == 5

        when:
        toolService.delete(toolId)
        sessionFactory.currentSession.flush()

        then:
        toolService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Tool tool = new Tool()
        toolService.save(tool)

        then:
        tool.id != null
    }
}
