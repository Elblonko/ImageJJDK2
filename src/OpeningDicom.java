/**
 * Created by elblonko on 7/8/14.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import ij.ImagePlus;
import ij.plugin.DICOM;



public class OpeningDicom {


    public static void main(String[] args) {
        File file = new File("/home/elblonko/Desktop/DICOMFiles/BRAINIX/sequence/IM-0001-0001.dcm");

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            System.out.println("Total file size to read (in bytes) : "
                    + fis.available());

            int content;
            /*
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }
            */

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //end of file try block



        DICOM dcm = new DICOM(fis);
        System.out.println("Before DICOM object");

        //dcm.run("Name");    //Causing an IOExceptoin
        dcm.show();

        System.out.println(dcm.getInfo("/home/elblonko/Desktop/DICOMFiles/BRAINIX/sequence/IM-0001-0001.dcm"));




    }

}