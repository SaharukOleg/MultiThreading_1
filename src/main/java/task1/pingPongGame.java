package task1;

public class pingPongGame {

    private static final Object object = new Object();
    private static volatile StringBuffer temp = new StringBuffer("  *");

    private void show() throws InterruptedException {
        System.out.println(" ]*");
        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                while (temp.length() <= 15) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp.insert(0, " ");
                    System.out.println(temp);
                    object.notify();
                }
            }
        }, "ping");

        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                while (temp.length() <= 15) {
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp.insert(0, " ");
                    System.out.println(temp);
                }
            }
        }, "pong");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        if (!thread1.isAlive() && !thread2.isAlive()) {
            temp.replace(temp.length() - 1, temp.length(), " *[");
            System.out.println(temp);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new pingPongGame().show();


    }

}

