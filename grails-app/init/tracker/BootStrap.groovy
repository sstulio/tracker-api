package tracker

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Tool) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['code'] = it.code
            output['image'] = it.image

            if(it.category) {
                output['category'] = ["id": it.category.id, "name": it.category.name]
            }

            output['location'] = ["id": it.location.id, "name": it.location.name]
            return output
        }

        JSON.registerObjectMarshaller(Worker) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['code'] = it.code
            output['image'] = it.image

            output['location'] = ["id": it.location.id, "name": it.location.name]
            return output
        }

        JSON.registerObjectMarshaller(Location) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['costCenter'] = it.costCenter
            output['tools'] = it.tools.collect { tool ->
                ["id": tool.id, "name": tool.name, "code": tool.code, "image": tool.code]
            }
            output['workers'] = it.workers.collect { worker ->
                ["id": worker.id, "name": worker.name, "code": worker.code, "image": worker.image]
            }
            return output
        }

        JSON.registerObjectMarshaller(Transition) {
            def output = [:]
            output['id'] = it.id
            output['transitionDate'] = it.transitionDate
            output['beforeLocation'] = ["id": it.beforeLocation.id, "name": it.beforeLocation.name]
            output['afterLocation'] = ["id": it.afterLocation.id, "name": it.afterLocation.name]
            output['tool'] = ["id": it.tool?.id, "name": it.tool?.name]
            output['worker'] = ["id": it.worker?.id, "name": it.worker?.name]

            return output
        }
    }
    def destroy = {
    }
}
