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
        //REVU: лучше бросьте exception в if, тогда else не нужен будет.
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        //REVU: лучше бросьте exception в if, тогда else не нужен будет.
        if (room != null && !room.equals("")) {
            this.room = room;
        } else {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        Collections.addAll(trainees, trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        //REVU: else можно убрать
        if (!trainees.contains(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        } else {
            trainees.remove(trainee);
        }
    }

    public void removeTrainee(int index) throws TrainingException {
        //REVU: else можно убрать
        if (trainees.size() - 1 < index) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        } else {
            trainees.remove(index);
        }
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        //REVU: переменную можно не заводить
        Trainee trainee = null;
        for (Trainee train : trainees) {
            if (train.getFirstName().equals(firstName)) {
                //REVU: если нашли трейни, то верните его, вместо break
                trainee = train;
                break;
            }
        }
        //REVU: и в конце концов этот if не нужен будет
        if (trainee == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return trainee;
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        //REVU: переменную можно не заводить
        Trainee trainee = null;
        for (Trainee train : trainees) {
            if (train.getFullName().equals(fullName)) {
                //REVU: если нашли трейни, то верните его, вместо break
                trainee = train;
                break;
            }
        }
        //REVU: и в конце концов этот if не нужен будет
        if (trainee == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return trainee;
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
        //REVU: лишний цикл
        for (int i = 5; i > 0; i--) {
            //REVU: заведите переменную maxRating и проверяйте, если текущий студент имеет рейтинг выше, чем maxRating
            //то очищаем список, добавляем туда студента и присваем новое значение для maxRating, если рейтинги совпадают
            // то просто добавляем в список
            for (Trainee trainee: trainees) {
                if (trainee.getRating() == i) {
                    list.add(trainee);
                }
            }
            if (!list.isEmpty()) {
                break;
            }
        }
        if (list.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return list;
    }

    public boolean hasDuplicates() {
        //REVU: заведите локальную переменную с типом Set. Пытайтесь туда положить trainee, если не получился, то возвращайте true
        for (Trainee trainee : trainees) {
            for (int i = trainees.indexOf(trainee) + 1; i < trainees.size(); i++) {
                if (trainee.equals(trainees.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) && room.equals(group.room) && trainees.equals(group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}
