package encryptedChat;

public class Client implements Runnable{
	int id;
	public Client(int id){
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("I am client" + id);
	}

}
