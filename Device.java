import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Device extends Thread{
  private String name;
  private String type;
  private final Router router;
  private final Network network;
  ArrayList<JLabel> labels= new ArrayList<>();

  //private int num;

  public Device(String name, String type, Router router ,Network network){
    this.name = name;
    this.type = type;
    this.router = router;
    this.network=network;
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

  public void createImages()
  {

    ImageIcon pc= new ImageIcon(Objects.requireNonNull(Network.class.getResource("pc1.png")));
    ImageIcon laptop= new ImageIcon(Objects.requireNonNull(Network.class.getResource("laptop.png")));
    ImageIcon tablet= new ImageIcon(Objects.requireNonNull(Network.class.getResource("tablet.JPG")));
    ImageIcon mobile= new ImageIcon(Objects.requireNonNull(Network.class.getResource("mobile.JPG")));

    // 1- pc 2- lap 3- tablet 4- mobile
    labels.add(new JLabel(pc));
    labels.add(new JLabel(laptop));
    labels.add(new JLabel(tablet));
    labels.add(new JLabel(mobile));
  }

  @Override
  public void run(){

    createImages();
    switch (type)
    {
      case "pc":
        network.getFrame().add(labels.get(0));
        break;
      case "laptop":
        network.getFrame().add(labels.get(1));
        break;
      case "tablet":
        network.getFrame().add(labels.get(2));
        break;
      case "mobile":
        network.getFrame().add(labels.get(3));
        break;
    }

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
