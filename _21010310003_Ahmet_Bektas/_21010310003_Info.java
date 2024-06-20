package _21010310003_Ahmet_Bektas;

public class _21010310003_Info {

	_21010310003_Reader reader;
    String[] programmeName;
	int[] startTime;
	int[] endTime;
	int[] dataPart;
	int[] codePart;
	int[] stackPart;
	int[] heapPart;
	
	public _21010310003_Info() {
		this.reader = new _21010310003_Reader();
		reader.readlines(reader.dosya);
		this.programmeName = new String[reader.lines.length];
		this.startTime = new int[reader.lines.length];
		this.endTime = new int[reader.lines.length];
		this.dataPart = new int[reader.lines.length];
		this.codePart = new int[reader.lines.length];
		this.stackPart = new int[reader.lines.length];
		this.heapPart = new int[reader.lines.length];
		this.insertParts();
	}
	
	public void insertParts() {
		for (int i = 0; i < reader.lines.length; i++) {
			String[] parts = reader.lines[i].split(" ");
			this.programmeName[i] = parts[0];
			this.startTime[i] = Integer.parseInt(parts[1]);
			this.endTime[i] = Integer.parseInt(parts[2]);
			this.dataPart[i] = Integer.parseInt(parts[3]);
			this.codePart[i] = Integer.parseInt(parts[4]);
			this.stackPart[i] = Integer.parseInt(parts[5]);
			this.heapPart[i] = Integer.parseInt(parts[6]);
			
		}
			
	}
	
	
}
