package serverSide;

public class GDDProtocol {
	
	private String dataToProcess;
	private String dataToSend = "";

	public GDDProtocol(String message) {
		this.dataToProcess = message;
	}
	
	public void updateData(String message) {
		this.dataToProcess = message;
	}
	
	private void traiterData() {
		
	}
	
}
