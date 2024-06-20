package _21010310003_Ahmet_Bektas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class _21010310003_RAM {

    _21010310003_Info info = new _21010310003_Info();
    final int OS_Size = 1000 * 1024;
    final int RamSize = 10000 * 1024;
    private TreeMap<Integer, Integer> ramUsage = new TreeMap<>();
    ArrayList<_21010310003_PCB> pcbList = new ArrayList<>(100);
    private Map<Integer, Integer> pidToIndexMap = new HashMap<>();
    int osEndAddress = OS_Size - 1;
    int processStartAddress = OS_Size;

    public _21010310003_RAM() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Lutfen RAM’in durumunu gormek istediginiz saniyeyi giriniz:");
        int time = sc.nextInt();
        System.out.println(time + ". Saniyede RAM’in dolu olan kisimlari:");
        

        for (int i = 0; i <= time; i++) {
            for (int x = 0; x < info.programmeName.length; x++) {
                if ((i == info.startTime[x]) ) {
                	int indexToAdd = x;
                	if (indexToAdd >= pcbList.size()) {
                	    int elementsToAdd = indexToAdd - pcbList.size() + 1;
                	    for (int j = 0; j < elementsToAdd; j++) {
                	        pcbList.add(null);
                	    }
                	}
                	this.pcbList.set(x,new _21010310003_PCB(i,x));
                    placeProcess(x);
                }
                if(i == info.endTime[x]) {
                	removeExpiredProcesses(x);
                }
            }
        }
        
        for (int i = 0; i < pcbList.size(); i++) {
            if (pcbList.get(i) != null) {
                pidToIndexMap.put(pcbList.get(i).pid, i);
            }
        }
        
        printRamUsage();
        printPCBInfo();
        
        System.out.println(time + ". saniyedeki PCB’sini goruntulemek istediginiz proses ismini giriniz: ");
        String processName = sc.next();
        
        System.out.println(processName + " isimli prosesin " +time+ " saniyedeki PCB bilgileri su sekildedir: " );
        givenProcessNameInfo(processName);
        
        
       

    }

    public void placeProcess(int x) {
        int dataSize = info.dataPart[x];
        int codeSize = info.codePart[x];
        int stackSize = info.stackPart[x];
        int heapSize = info.heapPart[x];
        int totalProcessSize = dataSize + codeSize + stackSize + heapSize;
        

        int startAddress = findFirstFit(totalProcessSize * 1024);
        if (startAddress != -1) {
            for (int i = startAddress; i < startAddress + totalProcessSize * 1024; i++) {
                ramUsage.put(i, pcbList.get(x).pid);
            }
        } else {
        }
    }

    public void removeExpiredProcesses(int i) {
        int pid = pcbList.get(i).pid;
        ArrayList<Integer> addressesToRemove = new ArrayList<>();

        for (int address : ramUsage.keySet()) {
            if (ramUsage.get(address) == pid) {
                addressesToRemove.add(address);
            }
        }

        for (int address : addressesToRemove) {
            ramUsage.remove(address);
        }
        pcbList.remove(i);
            
    }

    
    public int findFirstFit(int processSize) {
        int startAddress = -1;
        boolean found = false;

        for (int address = OS_Size; address < RamSize; address++) {
            if (!ramUsage.containsKey(address)) { 
                int currentBlockSize = 0;
                int currentAddress = address;

                while (!ramUsage.containsKey(currentAddress) && currentBlockSize < processSize) {
                    currentBlockSize++;
                    currentAddress++;
                }

                if (currentBlockSize >= processSize) {
                    found = true;
                    startAddress = address;
                    break;
                }
            }
        }

        return found ? startAddress : -1;
    }
    
    
    
    public void printRamUsage() {
    	System.out.println("0. ve "+ osEndAddress + ". Adresler arasinda isletim sistemi vardir. " );
        int startAddress = -1;
        int prevPid = -1;
        
        for (Map.Entry<Integer, Integer> entry : ramUsage.entrySet()) {
            int address = entry.getKey();
            int pid = entry.getValue();
            int i = 0;
            
            if (pid != prevPid) { 
                if (startAddress != -1) { 
                	Map.Entry<Integer, Integer> lastEntry = getLastEntryForPid(prevPid);
                	int pid2 = ramUsage.get(startAddress);
                    int pcbIndex = pidToIndexMap.get(pid2);
                        	System.out.println(startAddress + ". Ve " + lastEntry.getKey() + ". Adresler arasinda " + pcbList.get(pcbIndex).name + " programi bulunmaktadir.");
                	
                }
                startAddress = address; 
            }
            
            prevPid = pid;

        }
        
        Map.Entry<Integer, Integer> lastEntry = ramUsage.lastEntry();
        if (startAddress != -1) {
        	System.out.println(startAddress + ". Ve " + lastEntry.getKey() + ". Adresler arasinda " +pcbList.get(pcbList.size()-1).name + " programi bulunmaktadir.");

        }
    }
    
    
    private Map.Entry<Integer, Integer> getLastEntryForPid(int pid) {
        Map.Entry<Integer, Integer> lastEntry = null;
        for (Map.Entry<Integer, Integer> entry : ramUsage.entrySet()) {
            if (entry.getValue() == pid) {
                lastEntry = entry;
            }
        }
        return lastEntry;
    }
    

    public void printPCBInfo() {
    	
    	    System.out.print("PCB'si bulunan prosesler: ");
    	    for (_21010310003_PCB pcb : pcbList) {
    	    	if(pcb != null)
    	    		System.out.print(pcb.name + " ");
    	    	  
    	    }
    	    System.out.println();

    }
    
    public void givenProcessNameInfo(String processName) {
    	for (_21010310003_PCB pcb : pcbList) {
            if (pcb != null && pcb.name.equals(processName)) {
                System.out.println("Proses numarasi: " + pcb.pid);
                System.out.println("Prosesin yaratilma zamani: " + pcb.creationTime + ". saniye");
                System.out.println("Prosesin toplam buyuklugu: " + pcb.totalSize + " KB");
                break; 
            }
        }
    }
    

}
