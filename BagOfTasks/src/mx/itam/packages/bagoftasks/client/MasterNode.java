package mx.itam.packages.bagoftasks.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import mx.itam.packages.bagoftasks.interfaces.Bioinformatics;
import mx.itam.packages.bagoftasks.interfaces.DataMining;
import mx.itam.packages.bagoftasks.interfaces.ImageProcessing;
import mx.itam.packages.bagoftasks.serializableobjects.Task;


public class MasterNode {

    public static void main(String args[]) {

        System.setProperty("java.security.policy", "src/mx/itam/packages/bagoftasks/client/client.policy");

        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost";
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]
            // TaskID, serviceName, Tiempo a Tardar
            Task[] boTImageProcessing = {
                    new Task("T1", "ImageProcessing", (long) 5000),
                    new Task("T2", "ImageProcessing", (long) 10000),
                    new Task("T3", "ImageProcessing", (long) 15000),
                    new Task("T4", "ImageProcessing", (long) 20000),
                    new Task("T5", "ImageProcessing", (long) 30000),
                    new Task("T6", "ImageProcessing", (long) 5000),
                    new Task("T7", "ImageProcessing", (long) 10000),
                    new Task("T8", "ImageProcessing", (long) 15000),
                    new Task("T9", "ImageProcessing", (long) 20000),
                    new Task("T10", "ImageProcessing", (long) 30000),
            };

            Task[] boTDataMining = {
                    new Task("T11", "DataMining", (long) 5000),
                    new Task("T12", "DataMining", (long) 10000),
                    new Task("T13", "DataMining", (long) 15000),
                    new Task("T14", "DataMining", (long) 20000),
                    new Task("T15", "DataMining", (long) 30000),
                    new Task("T16", "DataMining", (long) 5000),
                    new Task("T17", "DataMining", (long) 10000),
                    new Task("T18", "DataMining", (long) 15000),
                    new Task("T19", "DataMining", (long) 20000),
                    new Task("T20", "DataMining", (long) 30000),
                    new Task("T21", "DataMining", (long) 5000),
                    new Task("T22", "DataMining", (long) 10000),
                    new Task("T23", "DataMining", (long) 15000),
                    new Task("T24", "DataMining", (long) 20000),
                    new Task("T25", "DataMining", (long) 30000),
                    new Task("T26", "DataMining", (long) 5000),
                    new Task("T27", "DataMining", (long) 10000),
                    new Task("T28", "DataMining", (long) 15000),
                    new Task("T29", "DataMining", (long) 20000),
                    new Task("T30", "DataMining", (long) 30000),
            };

            Task[] boTBioinformatics = {
                    new Task("T31", "Bioinformatics", (long) 5000),
                    new Task("T32", "Bioinformatics", (long) 10000),
                    new Task("T33", "Bioinformatics", (long) 15000),
                    new Task("T34", "Bioinformatics", (long) 20000),
                    new Task("T35", "Bioinformatics", (long) 30000),
                    new Task("T36", "Bioinformatics", (long) 5000),
                    new Task("T37", "Bioinformatics", (long) 10000),
                    new Task("T38", "Bioinformatics", (long) 15000),
                    new Task("T39", "Bioinformatics", (long) 20000),
                    new Task("T40", "Bioinformatics", (long) 30000),
                    new Task("T41", "Bioinformatics", (long) 5000),
                    new Task("T42", "Bioinformatics", (long) 10000),
                    new Task("T43", "Bioinformatics", (long) 15000),
                    new Task("T44", "Bioinformatics", (long) 20000),
                    new Task("T45", "Bioinformatics", (long) 30000)
            };

            long startTime = System.currentTimeMillis();
            System.out.println("Sending ImageProcessing BoT for execution");
            BoTExecution boTExecutionIP = new BoTExecution(boTImageProcessing, registry);
            boTExecutionIP.start();

            System.out.println("Sending DataMining BoT for execution");
            BoTExecution boTExecutionDM = new BoTExecution(boTDataMining, registry);
            boTExecutionDM.start();

            System.out.println("Sending Bioinformatics BoT for execution");
            BoTExecution boTExecutionBI = new BoTExecution(boTBioinformatics, registry);
            boTExecutionBI.start();

            // Los hilos los une al hilo principal
            boTExecutionIP.join();
            boTExecutionDM.join();
            boTExecutionBI.join();

            long endTime = System.currentTimeMillis();

            System.out.println("Seconds: " + String.valueOf((endTime - startTime) / 1000));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    private static class BoTExecution extends Thread {

        Task[] boT;
        Registry registry;

        public BoTExecution(Task[] boT, Registry registry) {
            this.boT = boT;
            this.registry = registry;
        }

        public void run() {
            try {

                Task taskType = boT[0];
                DataMining dm = null;
                ImageProcessing ip = null;
                Bioinformatics bi = null;

                // Busca el servicio según el caso
                if (taskType.getRequirementId().equals("DataMining")) {
                    dm = (DataMining) registry.lookup(taskType.getRequirementId());
                } else if (taskType.getRequirementId().equals("ImageProcessing")) {
                    ip = (ImageProcessing) registry.lookup(taskType.getRequirementId());
                } else if (taskType.getRequirementId().equals("Bioinformatics")) {
                    bi = (Bioinformatics) registry.lookup(taskType.getRequirementId());
                }

                for (int i = 0; i < boT.length; i++) {
                    Task task = boT[i];
                    System.out.println("Sending task " + task.getTaskId() + " for execution");

                    if (task.getRequirementId().equals("DataMining")) {
                        task = dm.executeDataMiningTask(task); // Ejecuta el servicio
                        System.out.println("Data Mining task " + task.getTaskId() + " was executed with output " + task.getOutput());
                    } else if (task.getRequirementId().equals("ImageProcessing")) {
                        task = ip.executeImageProcessingTask(task);
                        System.out.println("Image Processing task " + task.getTaskId() + " was executed with output " + task.getOutput());
                    } else if (task.getRequirementId().equals("Bioinformatics")) {
                        task = bi.executeBioinformaticsTask(task);
                        System.out.println("Bioinformatics task " + task.getTaskId() + " was executed with output " + task.getOutput());
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}   
