package hibiscus;

public class Person {

    private int personId;
    private String name;
    private String state;
    private String aboutMe;

    public Person(int personId, String name, String state, String aboutMe) {
        this.personId = personId;
        this.name = name;
        this.state = state;
        this.aboutMe = aboutMe;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public String toString() {
        return name;
    }
}
