import ij.IJ;
import ij.ImagePlus;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;
import vib.PointList;

/**
 * Created by elblonko on 7/15/14.
 */
public class DisplayDICOM3DHighlightPoints {

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


// Make the point list visible
        c.showPointList(true);

// Retrieve the point list
        PointList pl = c.getPointList();

// Add a few points
        pl.add(190, 450, 170);

        pl.add(330, 370, 300);

        pl.add(430, 90, 150);

        // Add a point at a specific canvas position
        univ.getPicker().addPoint(c, 256, 256);

        // Change the size of the points
        float curr = c.getLandmarkPointSize();
        c.setLandmarkPointSize(curr * 2);

        //creating and showing a movie



// animate the universe
        univ.startAnimation();


// record a 360 degree rotation around the y-axis
        ImagePlus movie = univ.record360();
        movie.show();
        univ.pauseAnimation();

    }
}
