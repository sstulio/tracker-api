package tracker

import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

class WorkerController {

    WorkerService workerService
    TransitionService transitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render workerService.list(params) as JSON
    }

    def show(Long id) {
        render workerService.get(id) as JSON
    }

    def save(Worker worker) {
        if (worker == null) {
            render status: NOT_FOUND
            return
        }

        try {
            workerService.save(worker)
        } catch (ValidationException e) {
            respond worker.errors, view: 'create'
            return
        }

        render worker as JSON
    }

    def transitions(Long id) {

        List<Transition> transitions = Transition.findAllByWorker(Worker.read(id))

        render transitions as JSON
    }

    def update(Worker worker) {
        try {

            if (worker == null) {
                render status: NOT_FOUND
                return
            }

            Location beforeLocation = worker.getPersistentValue('location')

            if (worker.location != beforeLocation) {
                Transition transition = new Transition()
                transition.setWorker(worker)
                transition.setBeforeLocation(beforeLocation)
                transition.setAfterLocation(worker.getLocation())
                transition.setTransitionDate(new Date())
                transitionService.save(transition)
            }

            workerService.save(worker)
            render worker as JSON

        } catch (ValidationException e) {
            respond worker.errors, view: 'edit'
            return
        }
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        Worker worker = Worker.read(id)

        List<Transition> transitions = Transition.findAllByWorker(worker)
        transitions.each {
            transitionService.delete(it.id)
        }

        workerService.delete(id)

        render status: NO_CONTENT
    }
}
