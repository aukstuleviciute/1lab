package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.inject.Specializes;
import java.util.Random;
import java.util.concurrent.Future;

@Specializes
public class ComplexStudentNumberGenerator extends StudentNumberGenerator {

    @Futureable
    public Future<Integer> generateStudentNumber() {
        try {
            Thread.sleep(4000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedStudentNumber = new Random().nextInt(300);
        return new AsyncResult<>(generatedStudentNumber);
    }
}