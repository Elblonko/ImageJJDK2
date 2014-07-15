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

        String path = new String("/home/elblonko/Desktop/ExportedDICOM/BrainX");


        ImagePlus dicomStackImage = IJ.openImage(path);

        new StackConverter(dicomStackImage).convertToGray8();

        //need to add this library
        Image3DUniverse univ = new Image3DUniverse();
        univ.show();

        // Add the image as a volume rendering
        /*
        In this case, the stack is displayed as a volume rendering. Volume renderings
        are created here by putting 2D slices of the stack one behind another. Different
        planes are thereby separated according to the pixel dimensions of the image. To
        each voxel, a transparency value is assigned, depending on its intensity. Black
        voxels are thereby fully transparent, whereas white ones are fully opaque.
         */
        Content c = univ.addVoltex(dicomStackImage);


        // Display the image as orthslices
        /*
        There exist 4 types of Contents: Volume Renderings, Orthoslices, Isosurfaces
        and Surface Plots. Contents which were created from an ImagePlus can be
        transferred from one display type into another:
         */
        //c.displayAs(Content.ORTHO);

        // Remove the Content from the universe
        //univ.removeContent(c.getName());

        /*
        Isosurfaces are surfaces which are generated here by applying the marching
        cubes algorithm. This algorithm assumes that there is one intensity value
        in the stack which separates a 3D object from its background. Therefore,
        the generated surface has theoretically everywhere the same (iso-)value.
        This value, also called the threshold of the surface, is adjustable, as will
        be seen later.
         */
        // Add an isosurface
         //c = univ.addMesh(dicomStackImage);


         /*
         Finally, a Content can be displayed as a surface plot. A surface plot is
         a 3D representation of a 2D slide, where the 3rd dimension is formed by
         the image intensity
          */
         //c = univ.addSurfacePlot(dicomStackImage);

        // remove all contents
        //univ.removeAllContents();
        // close
        //univ.close();







    }
}
