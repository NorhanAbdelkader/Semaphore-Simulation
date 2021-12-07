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

  public void logIn() throws InterruptedException{
    //- Connection 1: C1 login
    sleep(1500);
    System.out.println("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " login");
  }

  public void performsOnlineActivity() throws InterruptedException{
    //- Connection 2: C2 performs online activity
    sleep(1500);
    System.out.println("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " performs online activity");
  }

  public void logOut() throws InterruptedException{  
    sleep(1500);
    router.releaseConnection(this);
  }


  @Override
  public void run(){
    router.getSemaphore().wait(this);   
    try {
      router.fill(this);
      logIn();
      performsOnlineActivity();
      logOut();
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
