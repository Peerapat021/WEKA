import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WekaPredictor {
    
    public static void main(String[] args) {
        try {
            // Check if all required arguments are provided
            if (args.length < 8) {
                System.err.println("Usage: java WekaPredictor <model_path> <vendor> <MYCT> <MMIN> <MMAX> <CACH> <CHMIN> <CHMAX>");
                System.exit(1);
            }
            
            // Parse command line arguments
            String modelPath = args[0];
            String vendor = args[1];
            double myct = Double.parseDouble(args[2]);
            double mmin = Double.parseDouble(args[3]);
            double mmax = Double.parseDouble(args[4]);
            double cach = Double.parseDouble(args[5]);
            double chmin = Double.parseDouble(args[6]);
            double chmax = Double.parseDouble(args[7]);
            
            // Load the model
            Classifier cls = (Classifier) SerializationHelper.read(modelPath);
            
            // Create attributes list (must match the training data structure)
            ArrayList<Attribute> attributes = new ArrayList<>();
            
            // Create nominal attribute for Vendor with possible values
            ArrayList<String> vendorValues = new ArrayList<>();
            vendorValues.add("amdahl");
            vendorValues.add("apollo");
            vendorValues.add("basf");
            vendorValues.add("bti");
            vendorValues.add("burroughs");
            vendorValues.add("c r d");
            vendorValues.add("cambex");
            vendorValues.add("cdc");
            vendorValues.add("dec");
            vendorValues.add("dg");
            vendorValues.add("formation");
            vendorValues.add("four phase");
            vendorValues.add("gould");
            vendorValues.add("harris");
            vendorValues.add("honeywell");
            vendorValues.add("hp");
            vendorValues.add("ibm");
            vendorValues.add("ipl");
            vendorValues.add("magnuson");
            vendorValues.add("microdata");
            vendorValues.add("nas");
            vendorValues.add("ncr");
            vendorValues.add("nixdorf");
            vendorValues.add("perkin elmer");
            vendorValues.add("prime");
            vendorValues.add("siemens");
            vendorValues.add("sperry");
            vendorValues.add("stratus");
            vendorValues.add("wang");
            
            attributes.add(new Attribute("vendor", vendorValues));
            
            // Add numeric attributes
            attributes.add(new Attribute("MYCT"));
            attributes.add(new Attribute("MMIN"));
            attributes.add(new Attribute("MMAX"));
            attributes.add(new Attribute("CACH"));
            attributes.add(new Attribute("CHMIN"));
            attributes.add(new Attribute("CHMAX"));
            
            // Create nominal attribute for class (PerformanceClass)
            ArrayList<String> classValues = new ArrayList<>();
            classValues.add("low");
            classValues.add("medium");
            classValues.add("medium-low");
            classValues.add("medium-high");
            classValues.add("high");
            classValues.add("very-high");
            
            attributes.add(new Attribute("PerformanceClass", classValues));
            
            // Create dataset with attributes
            Instances dataUnpredicted = new Instances("CPUPerformance-Binned", attributes, 0);
            dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);
            
            // Create the instance
            DenseInstance inst = new DenseInstance(8);
            inst.setDataset(dataUnpredicted);
            
            // Set attribute values
            inst.setValue(0, vendor);
            inst.setValue(1, myct);
            inst.setValue(2, mmin);
            inst.setValue(3, mmax);
            inst.setValue(4, cach);
            inst.setValue(5, chmin);
            inst.setValue(6, chmax);
            
            // Add the instance to the dataset
            dataUnpredicted.add(inst);
            
            // Make prediction
            double prediction = cls.classifyInstance(dataUnpredicted.instance(0));
            
            // Get the class value
            String predictedClass = dataUnpredicted.classAttribute().value((int) prediction);
            
            // Output the result
            System.out.println(predictedClass);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}