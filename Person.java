public class Person implements Comparable<Person> {

    private String name;
    private String surname;
    private String adress;
    private Integer key;
    private PhoneNumber[] phoneNumbers;

    public Person() {
        this.name = "";
        this.surname = "";
        this.adress = "";
        phoneNumbers = new PhoneNumber[3];
        phoneNumbers[0] = new PhoneNumber("","");
        phoneNumbers[1] = new PhoneNumber("","");
        phoneNumbers[2] = new PhoneNumber("","");
    }

    public Person(String name,String surname,String adress,Integer key) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.key = key;
        phoneNumbers = new PhoneNumber[3];
        phoneNumbers[0] = new PhoneNumber("","");
        phoneNumbers[1] = new PhoneNumber("","");
        phoneNumbers[2] = new PhoneNumber("","");
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getKey() { return this.key; }

    public void setKey(Integer key) { this.key = key; }

    public PhoneNumber[] getPhoneNumber() {
        return this.phoneNumbers;
    }

    public void setPhoneNumber(String type,String number) {
        for(int i=0;i<3;i++) {
            if(phoneNumbers[i].getPhoneNumber() == "" && phoneNumbers[i].getPhoneType() == "") {
                phoneNumbers[i].setPhoneNumber(type,number);
                break;
            }
        }
    }

    public String toString() {
        return this.name + " " + this.surname + " " + this.adress;
    }

    public int compareTo(Person p)
    {
        if (name.equals(p.name)) {
            return surname.compareTo(p.surname);
        }
        else {
            return name.compareTo(p.name);
        }
    }

}
