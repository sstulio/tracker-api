package tracker

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CategoryController {

    CategoryService categoryService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond categoryService.list(params), model:[categoryCount: categoryService.count()]
    }

    def show(Long id) {
        respond categoryService.get(id)
    }

    def save(Category category) {
        if (category == null) {
            render status: NOT_FOUND
            return
        }

        try {
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors, view:'create'
            return
        }

        respond category, [status: CREATED, view:"show"]
    }

    def update(Category category) {
        if (category == null) {
            render status: NOT_FOUND
            return
        }

        try {
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors, view:'edit'
            return
        }

        respond category, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        categoryService.delete(id)

        render status: NO_CONTENT
    }
}
