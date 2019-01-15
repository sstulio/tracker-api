package tracker

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TransitionController {

    TransitionService transitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render transitionService.list(params) as JSON
    }

    def show(Long id) {
        render transitionService.get(id) as JSON
    }

    def save(Tool tool) {
        if (tool == null) {
            render status: NOT_FOUND
            return
        }

        try {
            transitionService.save(tool)
        } catch (ValidationException e) {
            respond tool.errors, view:'create'
            return
        }

        render tool as JSON
    }

    def update(Tool tool) {
        if (tool == null) {
            render status: NOT_FOUND
            return
        }

        try {
            transitionService.save(tool)
        } catch (ValidationException e) {
            respond tool.errors, view:'edit'
            return
        }

        render tool as JSON
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        transitionService.delete(id)

        render status: NO_CONTENT
    }
}
