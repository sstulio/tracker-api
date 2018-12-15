package tracker

import grails.gorm.services.Service

@Service(Tool)
interface ToolService {

    Tool get(Serializable id)

    List<Tool> list(Map args)

    Long count()

    void delete(Serializable id)

    Tool save(Tool tool)

}