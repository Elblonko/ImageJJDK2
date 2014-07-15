import ij.IJ;
import ij.ImagePlus;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;

/**
 * Created by elblonko on 7/14/14.
 */
public class ChangeColorTransparency3D {

    public static void main(String [] args) {

        String path = new String("/home/elblonko/Desktop/ExportedDICOM/BrainX");


        ImagePlus imp = IJ.openImage(path);
        new StackConverter(imp).convertToGray8();

        //Create and show universe
        Image3DUniverse univ = new Image3DUniverse();
        univ.show();

        // Add the image as an isosurface
        Content c = univ.addMesh(imp);

        // Display the Content in purple
        c.setColor(new Color3f(0.5f, 0, 0.5f));


        // Make it transparent
        c.setTransparency(2.5f);

        // Add the image as an isosurface
        //Content c = univ.addVoltex(imp);


        // Create a new Transform3D object
        Transform3D t3d = new Transform3D();

        // Make it a 45 degree rotation around the local y-axis
        t3d.rotY(45 * Math.PI / 180);

        // Apply the transformation to the Content. This concatenates
        // the previous present transformation with the specified one
        c.applyTransform(t3d);



    }
}
