import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.plugin.DICOM;
import ij.process.StackConverter;
import ij.util.DicomTools;
import ij3d.Content;
import ij3d.Image3DUniverse;

import java.io.File;
import java.util.Arrays;

/**
 * Created by elblonko on 7/13/14.
 */
public class DisplayDicom3D {

    public static void main(String [] args){

        String path = new String("/home/elblonko/Desktop/ExportedDICOM/BrainXtiff");


        ImagePlus dicomStackImage = IJ.openImage(path);

        new StackConverter(dicomStackImage).convertToGray8();

        //need to add this library
        Image3DUniverse univ = new Image3DUniverse();
        univ.show();

        // Add the image as a volume rendering
        Content c = univ.addVoltex(dicomStackImage);


        // Display the image as orthslices
        c.displayAs(Content.ORTHO);

        // Remove the Content
        univ.removeContent(c.getName());

        // Add an isosurface
        c = univ.addMesh(dicomStackImage);

        c = univ.addSurfacePlot(dicomStackImage);






    }
}
