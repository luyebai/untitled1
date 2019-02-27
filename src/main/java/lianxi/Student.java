package lianxi;

public class Student {
    int id;
    String name;
    int score;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Student(int id, String name, int score) {

        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
