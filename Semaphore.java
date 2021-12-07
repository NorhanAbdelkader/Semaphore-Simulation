public class Semaphore {

  private int connections;

  public Semaphore(){
    connections = 0;
  }

  public Semaphore(int val){
    connections = val;
  }

  public synchronized void wait (Device device){
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
      return;
    }
    System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived.");
  }

  public synchronized void signal (){
    connections ++;
    if (connections <= 0)
    {
      notify();
    }
  }
}
