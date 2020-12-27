package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private final Queue<Trainee> queueTrainee;

    public TraineeQueue() {
        queueTrainee = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        queueTrainee.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        if (queueTrainee.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        return queueTrainee.remove();
    }

    public boolean isEmpty() {
        return queueTrainee.size() == 0;
    }
}
