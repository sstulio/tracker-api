package tracker

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LocationController {

    LocationService locationService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render locationService.list(params) as JSON
    }

    def show(Long id) {
        render locationService.get(id) as JSON
    }

    def save(Location location) {
        if (location == null) {
            render status: NOT_FOUND
            return
        }

        try {
            locationService.save(location)
        } catch (ValidationException e) {
            respond location.errors, view:'create'
            return
        }

        respond location, [status: CREATED, view:"show"]
    }

    def update(Location location) {
        if (location == null) {
            render status: NOT_FOUND
            return
        }

        try {
            locationService.save(location)
        } catch (ValidationException e) {
            respond location.errors, view:'edit'
            return
        }

        respond location, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        locationService.delete(id)

        render status: NO_CONTENT
    }
}
