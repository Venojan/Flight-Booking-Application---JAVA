public class Person {
    private String Name;
    private String Surname;
    private String Email;

    public Person(String name, String surname, String email) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email=email;
    }
}
