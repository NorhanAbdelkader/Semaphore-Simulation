public class Device extends Thread{
  private String name;
  private String type;
  private Router router;

  //private int num;

  public Device(String name, String type, Router router){
    this.name = name;
    this.type = type;
    this.router = router;
  }
  @Override
  public void run(){
    router.getSemaphore().wait(this);   
    try {
      router.fill(this);
      router.logIn(this);
      router.performsOnlineActivity(this);
      router.logOut(this);
    } 
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    router.getSemaphore().signal();

  }


  public void setDeviceName(String name){
    this.name = name;
  }

  public void setType(String type){
    this.type = type;
  }
  public String getDeviceName(){
    return name;
  }
  public String getType(){
    return type;
  }

}
