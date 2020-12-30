package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private final List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
        trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        Collections.addAll(trainees, trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.contains(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(trainee);
    }

    public void removeTrainee(int index) throws TrainingException {
        if (trainees.size() - 1 < index) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee train : trainees) {
            if (train.getFirstName().equals(firstName)) {
                return train;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee train : trainees) {
            if (train.getFullName().equals(fullName)) {
                return train;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparing(Trainee::getRating).reversed());
    }

    public void reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        List<Trainee> list = new ArrayList<>();
        int maxRating = 0;
        for (Trainee trainee: trainees) {
            if(trainee.getRating() > maxRating){
                list.clear();
                list.add(trainee);
                maxRating = trainee.getRating();
            } else if (trainee.getRating() == maxRating) {
                list.add(trainee);
            }
        }
        if (list.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return list;
    }

    public boolean hasDuplicates() {
        Set<Trainee> traineesSet = new HashSet<>();
        for (Trainee trainee : trainees) {
            if (!traineesSet.add(trainee)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) && Objects.equals(room, group.room) && Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}
