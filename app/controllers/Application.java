package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;

import views.html.*;

public class Application extends Controller {

    static Form<Task> taskForm = Form.form(Task.class);

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks() {
        return ok(
            views.html.index.render(Task.all(), taskForm)
        );
    }

    public static Result newTask() {
        Form<Task> filledform = taskForm.bindFromRequest();
        if (filledform.hasErrors()) {
            return badRequest(
                views.html.index.render(Task.all(), filledform)
            );
        } else {
            Task.create(filledform.get());
            return redirect(routes.Application.tasks());
        }
    }

    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }
}
