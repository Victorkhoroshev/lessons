package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private final Map<Trainee, String> mapTrainee;

    public TraineeMap() {
        mapTrainee = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (mapTrainee.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        mapTrainee.put(trainee, institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (!mapTrainee.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        mapTrainee.replace(trainee, institute);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException{
        if (!mapTrainee.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        mapTrainee.remove(trainee);
    }

    public int getTraineesCount() {
        return mapTrainee.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if (!mapTrainee.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return mapTrainee.get(trainee);
    }

    public Set<Trainee> getAllTrainees() {
        return mapTrainee.keySet();
    }

    public Set<String> getAllInstitutes() {
        return new HashSet<>(mapTrainee.values());
    }

    public boolean isAnyFromInstitute(String institute) {
        return mapTrainee.containsValue(institute);
    }
}
