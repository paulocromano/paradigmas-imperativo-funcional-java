import util.Execute;
import util.ExecuteService;

public class Main {

	public static void main(String[] args) {
		Execute execute = ExecuteService.CLIENTE.getExecute();
		execute.execute();
	}
}
