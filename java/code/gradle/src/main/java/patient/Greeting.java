package patient;

// basic class for all entries
public class Greeting {

  private String fname;
  private String lname;
  private String month;
  private String day;
  private String year;
  private String email;
  private String addr1;
  private String addr2;
  private String city;
  private String state;
  private String zip;

  public String getFname(){
    return fname;
  }
  public String getLname(){
    return lname;
  }
  public String getMonth(){
    return month;
  }
  public String getDay(){
    return day;
  }
  public String getYear(){
    return year;
  }
  public String getEmail(){
    return email;
  }
  public String getAddr1(){
    return addr1;
  }
  public String getAddr2(){
    return addr2;
  }
  public String getCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public String getZip(){
    return zip;
  }

  public void setFname(String fname){
    this.fname = fname;
  }
  public void setLname(String lname){
    this.lname = lname;
  }
  public void setMonth(String month){
    this.month = month;
  }
  public void setDay(String day){
    this.day = day;
  }
  public void setYear(String year){
    this.year = year;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public void setAddr1(String addr1){
    this.addr1 = addr1;
  }
  public void setAddr2(String addr2){
    this.addr2 = addr2;
  }
  public void setCity(String city){
    this.city = city;
  }
  public void setState(String state){
    this.state = state;
  }
  public void setZip(String zip){
    this.zip = zip;
  }

  @Override
  public String toString() {
    return "Patient [fname=" + fname +
            ", lname=" + lname +
            ", month=" + month +
            ", addr1=" + addr1
        + "]";
 }
}
