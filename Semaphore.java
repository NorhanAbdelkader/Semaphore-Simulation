public class Semaphore {

  private int connections;
  private Boolean waiting;
  public Semaphore(){
    connections = 0;
  }

  public Semaphore(int val){
    connections = val;
    //waiting= false;
  }

  public synchronized Boolean wait (Device device){
    connections --;
    if (connections < 0)
    {
      try {
        System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived and waiting.");
        wait();

      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      return true;
    }
    System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived.");
    return false; // not waiting
  }

  public synchronized void signal (){
    connections ++;
    if (connections <= 0)
    {
      notify();
    }
  }
}
