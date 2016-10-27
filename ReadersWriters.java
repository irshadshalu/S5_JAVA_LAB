class Database{
	public int readers;
	public Database(){
		this.readers = 0;
	}
	public void read(int reader_id){
		synchronized(this){
			this.readers++;
			System.out.println("Reader "+reader_id+" starts reading.");
		}
		try{
			Thread.sleep(500+(int)Math.random()*5000);
		}
		catch(Exception e){}

		synchronized(this){
			System.out.println("Reader "+reader_id+" stops reading.");
			this.readers--;
			if(readers == 0)
				this.notifyAll();
		}
	}
	public synchronized void write(int writer_id){
		while(this.readers != 0){
			try{
				this.wait();
			}catch(Exception e){}
		}
		System.out.println("Writer "+writer_id+" starts writing.");
		try{
			Thread.sleep(500+(int)Math.random()*5000);
		}catch (Exception e) {}

		System.out.println("Writer " + writer_id + " stops writing.");
		this.notifyAll();
	}
}

class Reader extends Thread{
	private static int readers = 0;
	private int number;
	private Database database;
	public Reader(Database database){
		this.number = Reader.readers++;
		this.database = database;
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(500+(int)Math.random()*5000);
			}
			catch(Exception e){}
			this.database.read(this.number);
		}
	}
}

class Writer extends Thread{
	private static int writers = 0;
	private int number;
	private Database database;
	public Writer(Database database){
		this.number = Writer.writers++;
		this.database = database;
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(500+(int)Math.random()*5000);
			}catch(Exception e){}
			this.database.write(this.number);
		}
	}
}

public class ReadersWriters{
	public static void main(String[] args){
		Database database = new Database();
		new Reader(database).start();
		new Reader(database).start();
		new Reader(database).start();
		new Writer(database).start();
		new Writer(database).start();	
	}
}