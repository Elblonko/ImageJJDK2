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
 * Created by elblonko on 7/8/14.
 */
public class DisplayDicomSequence {

    public static void main(String [] args){

        /* //Display the 200 long whole head sequence
        //Need path to a single image to get the size and width of each file that will be in the sequence
        String path = new String("/home/elblonko/Desktop/DICOMFiles/0.9mm200Sequence/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/0.9mm200Sequence");
        */

        /* //Thin Top Down slice of the brain
        //Need path to a single image to get the size and width of each file that will be in the sequence
        String path = new String("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-2.20100114.092220/0002_t2_tse_tra_p2_3mm/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-2.20100114.092220/0002_t2_tse_tra_p2_3mm");
        */

        /* // Not terribly interesting volume
        String path = new String("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-2.20100114.092220/0014_ep2d_diff_3scan_trace_p8/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-2.20100114.092220/0014_ep2d_diff_3scan_trace_p8");
        */

        /* //Larger Size Top Down view of the brain 30 slices thick
        String path = new String("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0003_axial-t2_tse-3mm/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0003_axial-t2_tse-3mm");
        */

        /* //Best Sequence yet showing only the brain 30 slices deep
        String path = new String("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0008_ep2d_diff_mddw_20_p2/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0008_ep2d_diff_mddw_20_p2");
        */

        /* //Not a volume displays many cuts of a brain at once with activity indicated
        String path = new String("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0006_ep2d_diff_mddw_20_p2/0001-0001.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/davetest_butler-dave-1.20100111.114822/0006_ep2d_diff_mddw_20_p2");
        */


        /* //200 Sequence Starting from 90
        //Need path to a single image to get the size and width of each file that will be in the sequence
        String path = new String("/home/elblonko/Desktop/DICOMFiles/0.9mm200SequenceStart90/0001-0090.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/0.9mm200SequenceStart90");
        */

         //200 Sequence Starting from 90
        //Need path to a single image to get the size and width of each file that will be in the sequence
        String path = new String("/home/elblonko/Desktop/DICOMFiles/0.9mm200Sequence/0001-0090.dcm");
        //The directory containing the files so you can iterate through and create an array of the images
        File fileDir = new File("/home/elblonko/Desktop/DICOMFiles/0.9mm200Sequence");







        //get num of files
        int numFiles = fileDir.listFiles().length;

        //Check to see if num files is correct
        System.out.println("Number of Files: " + numFiles);

        //generate the DICOM object for use
        DICOM dcm = new DICOM();

        //Now open the DICOM object using a path to a single image
        dcm.open(path);

        //Ensure image has width
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

        //Create a stack of DICOM images. Use the single image to get width and height params
        ImageStack dcmStack = new ImageStack(dcm.getWidth(),dcm.getHeight());

        //List the directory of files to use as an iterator
        File[] directoryListing = fileDir.listFiles();

        //Sort the file alphabetically
        Arrays.sort(directoryListing);


        if(directoryListing != null){

            for(File child: directoryListing){

                //open DICOM
                dcm.open(child.getAbsolutePath());

                System.out.println(child.toString());

                //Add DICOM to DicomStack
                dcmStack.addSlice(dcm.getProcessor());

            }
        }

        //Now sort the stack //TODO so far does not actually sort images
        DicomTools dTools = new DicomTools();
        dTools.sort(dcmStack);

        ImageStack sortedDcmStack = dTools.sort(dcmStack);

        //Get and print the Voxel Depth of the DicomStack
        double voxelDepth = dTools.getVoxelDepth(dcmStack);
        System.out.println("Voxel Depth of Stack: " + voxelDepth +
                " Times Loop ran: " + " Size of Stack: " +
                dcmStack.getSize());

        //now we construct an image plus from the image stack

        /*
        The first argument will be used as the title of the window that displays the image
        the second is the imagestack.
         */
        ImagePlus dicomStackImage = new ImagePlus("Andrews 0.9mm",sortedDcmStack);

        //THIS WORKS
        dicomStackImage.show();

        //trying to get a .draw() to work I think i need to create an "
        //dicomStackImage.draw();

        //trying to save the image as diff file types
        FileSaver saveFile = new FileSaver(dicomStackImage);

         //BOTH OF THESE FILE SAVES WORKS. THOUGH jpeg only works for a single slice
        //saveFile.saveAsJpeg("/home/elblonko/Desktop/ExportedDICOM/BrainXjpg");
        saveFile.saveAsTiffStack("/home/elblonko/Desktop/ExportedDICOM/WholeHead90-tiff");

        //Creates and Opener and then an open prompt, this prompt can open and display tiff images
        //Opener openStuff = new Opener();
        //openStuff.open();



    }
}
