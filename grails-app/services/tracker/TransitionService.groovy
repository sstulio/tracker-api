package tracker

import grails.gorm.services.Service

@Service(Transition)
interface TransitionService {

    Transition get(Serializable id)

    List<Transition> list(Map args)

    Long count()

    void delete(Serializable id)

    Transition save(Transition transition)

}