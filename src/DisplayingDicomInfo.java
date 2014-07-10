import ij.IJ;
import ij.plugin.DICOM;

/**
 * Created by elblonko on 7/8/14.
 */


public class DisplayingDicomInfo {
    public static void main(String [] args){

        String path = new String("/home/elblonko/Desktop/DICOMFiles/BRAINIX/sequence/IM-0001-0001.dcm");

        DICOM dcm = new DICOM();
        dcm.open(path);
        if (dcm.getWidth()==0)
            IJ.log("Error opening '" + path + "'");
        else
            dcm.show();

        int width = dcm.getWidth();
        int height = dcm.getHeight();
        String title = dcm.getTitle();
        int type = dcm.getType();

        System.out.println("title: " + title + " width: " + width + " height: " + height
        + " type: " +type);


    }
}
