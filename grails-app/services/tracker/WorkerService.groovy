package tracker

import grails.gorm.services.Service

@Service(Worker)
interface WorkerService {

    Worker get(Serializable id)

    List<Worker> list(Map args)

    Long count()

    void delete(Serializable id)

    Worker save(Worker Worker)

}