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
            output['category'] = ["id": it.category.id, "name": it.category.name]
            output['location'] = ["id": it.location.id, "name": it.location.name]
            return output
        }
    }
    def destroy = {
    }
}
