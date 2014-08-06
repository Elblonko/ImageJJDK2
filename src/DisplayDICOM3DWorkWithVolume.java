import ij.IJ;
import ij.ImagePlus;
import ij.gui.OvalRoi;
import ij.gui.Roi;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;
import voltex.VoltexGroup;

/**
 * Created by elblonko on 7/15/14.
 */
public class DisplayDICOM3DWorkWithVolume {

    public static void main(String [] args) {

        String path = new String("/home/elblonko/Desktop/ExportedDICOM/0.9-200-Sequencetiff");


        ImagePlus imp = IJ.openImage(path);

        new StackConverter(imp).convertToGray8();

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
        Content c;

        // Add the image as a volume
        c = univ.addVoltex(imp);



// Retrieve the VoltexGroup
        VoltexGroup voltex = (VoltexGroup)c.getContent();

// Define a ROI
        Roi roi = new OvalRoi(240, 220, 70, 50);

// Define a fill color
        byte fillValue = (byte)100;

// Fill the part of the volume which results from the
// projection of the polygon onto the volume:
        voltex.fillRoi(univ.getCanvas(), roi, fillValue);


    }
}
