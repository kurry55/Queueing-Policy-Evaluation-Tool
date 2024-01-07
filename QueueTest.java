//package project2;

public class QueueTest {
	public static void main(String[] args) {
		Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
        String[] items1 = { "Q1" };
        InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
        Request[] rList1 = { r11 };
        Queue[] queues = new Queue[1];

        client1.setRequests(rList1);

        Queue q1 = new Queue("Noah", 2);
        QueueSystem qS = new QueueSystem(1, false, false);
        QueueSystem.setQueues(queues);

        Client[] clientsInWorld = new Client[1];
        clientsInWorld[0] = client1;
        QueueSystem.setClientsWorld(clientsInWorld);

        queues[0] = q1;
        
        qS.increaseTime();

        System.out.println("Clock: " + QueueSystem.getClock());

        String output1 = q1.toString();
        String output2 = q1.toString(false);
        System.out.println("Output 1: " + output1.replaceAll(" ", ""));
        System.out.println("Output 2: " + output2.replaceAll(" ", ""));
        
        
        
    }

}

