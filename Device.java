import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Device extends Thread{
  private String name;
  private String type;
  private final Router router;
  private final Network network;
  private JLabel label;
  //private ArrayList<JLabel> labels= new ArrayList<>();

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

  public ArrayList<JLabel> createImages()
  {

    ArrayList<JLabel> labels= new ArrayList<>();

    ImageIcon pc= new ImageIcon(Objects.requireNonNull(Network.class.getResource("pc1.png")));
    ImageIcon laptop= new ImageIcon(Objects.requireNonNull(Network.class.getResource("laptop.png")));
    ImageIcon tablet= new ImageIcon(Objects.requireNonNull(Network.class.getResource("tablet2.png")));
    ImageIcon mobile= new ImageIcon(Objects.requireNonNull(Network.class.getResource("m2.png")));
    ImageIcon unknownDevice= new ImageIcon(Objects.requireNonNull(Network.class.getResource("d.png")));


    // 1- pc 2- lap 3- tablet 4- mobile
    labels.add(new JLabel(pc));
    labels.add(new JLabel(laptop));
    labels.add(new JLabel(tablet));
    labels.add(new JLabel(mobile));
    labels.add(new JLabel(unknownDevice));

    for(int i = 0 ; i < 5; i ++){
      labels.get(i).setVisible(false);
    }

    return labels;

  }
  public void showImages(){
    ArrayList<JLabel> labels = createImages();

      switch (type)
      {
        case "pc":
          labels.get(0).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(0));
          labels.get(0).setVisible(true);
          label = labels.get(0);

          break;
        case "laptop":
          labels.get(1).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(1));
          labels.get(1).setVisible(true);
          label = labels.get(1);

  
          break;
        case "tablet":
          labels.get(2).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(2));
          labels.get(2).setVisible(true);
          label = labels.get(2);

  
          break;
        case "mobile":
          labels.get(3).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(3));
          labels.get(3).setVisible(true);
          label = labels.get(3);

  
          break;
        default:
          labels.get(4).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(4));
          labels.get(4).setVisible(true);
          label = labels.get(4);

  
          break;
  
      }
  }

  @Override
  public void run() {

    router.getSemaphore().wait(this);
    showImages();

    try {
      router.fill(this);
      logIn();
      performsOnlineActivity();
      logOut();
      label.setVisible(false);

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
