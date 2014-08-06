import javax.vecmath.Point3d;

import orthoslice.OrthoGroup;
import ij.process.StackConverter;
import ij.plugin.PlugIn;
import ij.ImagePlus;
import ij.IJ;

import ij3d.AxisConstants;
import ij3d.Content;
import ij3d.Image3DUniverse;

/**
 * Created by elblonko on 7/15/14.
 */
public class DisplayDICOM3DWorldWithOrtho {

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


        // Add the image as a volume
        c = univ.addOrthoslice(imp);


// Retrieve the OrthoGroup
        OrthoGroup ortho = (OrthoGroup)c.getContent();

        for(int i = 0; i < 10; i++) {
            ortho.increase(AxisConstants.Z_AXIS);
            //sleep(1);
        }

// Hide the x-axis
        ortho.setVisible(AxisConstants.X_AXIS, false);


// Show it again and hide the z-axis
        ortho.setVisible(AxisConstants.X_AXIS, true);
        ortho.setVisible(AxisConstants.Z_AXIS, false);


// Show it again
        ortho.setVisible(AxisConstants.Z_AXIS, true);

    }
}
