package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.StudentNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateStudentNumber implements Serializable {
    @Inject
    StudentNumberGenerator studentNumberGenerator;

    private Future<Integer> studentNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewStudentNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        studentNumberGenerationTask = studentNumberGenerator.generateStudentNumber();
        return  "/studentDetails.xhtml?faces-redirect=true&studentId=" + requestParameters.get("studentId");
    }

    public boolean isStudentGenerationRunning() {
        return studentNumberGenerationTask != null && !studentNumberGenerationTask.isDone();
    }

    public String getStudentGenerationStatus() throws ExecutionException, InterruptedException {
        if (studentNumberGenerationTask == null) {
            return null;
        } else if (isStudentGenerationRunning()) {
            return "Student generation in progress";
        }
        return "Suggested student number: " + studentNumberGenerationTask.get();
    }
}