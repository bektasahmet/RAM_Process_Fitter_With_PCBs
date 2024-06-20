package _21010310003_Ahmet_Bektas;

public class _21010310003_PCB {
	
	_21010310003_Info info = new _21010310003_Info();
	static int nextPid = 1000;
	int pid;
	int creationTime;
	int totalSize;
	String name;
	
	
	public _21010310003_PCB(int pid, int x) {
		this.pid = nextPid++;
		this.creationTime = info.startTime[x];
		this.totalSize = info.dataPart[x] + info.codePart[x] + info.heapPart[x] + info.stackPart[x];
		this.name = info.programmeName[x];
		
		
		
	}
	
	
	
}
