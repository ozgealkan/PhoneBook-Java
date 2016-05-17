public class PhoneNumber {

    private String type;
    private String number;

    public PhoneNumber() {
        type="";
        number="";
    }

    public PhoneNumber(String type,String number) {
        this.type = type;
        this.number = number;
    }

    public String getPhoneType() {
        return this.type;
    }

    public String getPhoneNumber() {
        return this.number;
    }

    public void setPhoneNumber(String type,String number) {
        this.type = type;
        this.number = number;
    }

    public String toString() {
        return this.type + ":" + this.number;
    }

}
