package tracker

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ToolController {

    ToolService toolService
    TransitionService transitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render toolService.list(params) as JSON
    }

    def show(Long id) {
        render toolService.get(id) as JSON
    }

    def save(Tool tool) {
        if (tool == null) {
            render status: NOT_FOUND
            return
        }

        try {
            toolService.save(tool)
        } catch (ValidationException e) {
            respond tool.errors, view: 'create'
            return
        }

        render tool as JSON
    }

    def update(Tool tool) {
        try {

            if (tool == null) {
                render status: NOT_FOUND
                return
            }

            Location beforeLocation = tool.getPersistentValue('location')

            if (tool.location != beforeLocation) {
                Transition transition = new Transition()
                transition.setTool(tool)
                transition.setBeforeLocation(beforeLocation)
                transition.setAfterLocation(tool.getLocation())
                transition.setTransitionDate(new Date())
                transitionService.save(transition)
            }

            toolService.save(tool)
            render tool as JSON

        } catch (ValidationException e) {
            respond tool.errors, view: 'edit'
            return
        }
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        toolService.delete(id)

        render status: NO_CONTENT
    }
}
