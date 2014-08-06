import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.plugin.DICOM;
import ij.util.DicomTools;

import java.io.File;
import java.util.Arrays;

/**
 * Created by elblonko on 7/14/14.
 */
public class SaveDICOMAsTiff {

    public static void main(String [] args){

        String path = new String("/home/elblonko/Desktop/DICOMFiles/BRAINIX/sequence/IM-0001-0001.dcm");

        //Number of files in the path
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/BRAINIX/sequence");
        int numFiles = fileDir.listFiles().length;

        //Check to see if num files is correct
        System.out.println("Number of Files: " + numFiles);

        DICOM dcm = new DICOM();

        dcm.open(path);

        if (dcm.getWidth()==0)
            IJ.log("Error opening '" + path + "'");
        /*
        else
            dcm.show();
        */

        //create a image stack of DICOM files PROOF OF SINGLE CASE
        /*
        ImageStack dcmStack = new ImageStack(dcm.getWidth(),dcm.getHeight(),numFiles);
        dcmStack.addSlice(dcm.getProcessor());
        */

        ImageStack dcmStack = new ImageStack(dcm.getWidth(),dcm.getHeight());
        File[] directoryListing = fileDir.listFiles();

        //Sort the file alphabetically
        Arrays.sort(directoryListing);


        if(directoryListing != null){
            for(File child: directoryListing){

                //open DICOM
                dcm.open(child.getAbsolutePath());

                //Add DICOM to DicomStack
                dcmStack.addSlice(dcm.getProcessor());

                //System.out.println(child.toString());


            }
        }

        //Now sort the stack

        //Generate the DicomTool
        DicomTools dTools = new DicomTools();
        dTools.sort(dcmStack);
        //Pass in the stack to the sorted
        ImageStack sortedDcmStack = dTools.sort(dcmStack);


        //Get and print the Voxel Depth of the DicomStack
        /*
        double voxelDepth = dTools.getVoxelDepth(dcmStack);
        System.out.println("Voxel Depth of Stack: " + voxelDepth +
                " Times Loop ran: " + " Size of Stack: " +
                dcmStack.getSize());
        */

        //now we construct an image plus from the image stack

        /*
        The first argument will be used as the title of the window that displays the image
        the second is the imagestack.
         */
        ImagePlus dicomStackImage = new ImagePlus("BRAINX",sortedDcmStack);



        //trying to save the image as tiff file types
        FileSaver saveFile = new FileSaver(dicomStackImage);

        //BOTH OF THESE FILE SAVES WORKS. THOUGH jpeg only works for a single slice
        saveFile.saveAsJpeg("/home/elblonko/Desktop/ExportedDICOM/BrainXjpg");
        saveFile.saveAsTiffStack("/home/elblonko/Desktop/ExportedDICOM/BrainXtiff");


        //Allows for opening a Stack of DICOM images saved as a Tif file.
        //Opener openStuff = new Opener();

        //openStuff.open();

    }
}
