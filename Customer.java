public class Customer {
    private String name;
    private String address;
    private int phonenumber;

    public Customer(
            String name, String address, int phonenumber
    ){
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;

    }
    public String getName(){
        return name;
    }
    private void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public int getphonenumber(){
        return phonenumber;
    }
    public void setphonenumber(int phonenumber){
        this.phonenumber = phonenumber;
    }
    @Override
    public String toString(){
        return String.format("Name : %s Author : %s Phone : %d", this.getName(), this.getAddress(), this.getphonenumber());
    }
}
