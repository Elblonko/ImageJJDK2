import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import customnode.*;

import ij.plugin.PlugIn;
import ij.IJ;

import ij3d.Image3DUniverse;
import ij3d.Content;

/**
 * Created by elblonko on 7/15/14.
 */
public class Display3DCustomMeshes {

    public static void main(String args[]){


        // Create a set of points
        List<Point3f> mesh = new ArrayList<Point3f>();
        mesh.add(new Point3f(-100, -100, 50));
        mesh.add(new Point3f(100, -100, 50));
        mesh.add(new Point3f(0, 100, 50));

// Create a universe and show it
        Image3DUniverse univ = new Image3DUniverse();
        univ.showAttribute(
                Image3DUniverse.ATTRIBUTE_COORD_SYSTEM, false);
        univ.show();

// Add the mesh as points
        CustomPointMesh cm = new CustomPointMesh(mesh);
        cm.setColor(new Color3f(0, 1, 0));
        univ.addCustomMesh(cm, "points");
        cm.setPointSize(10);

// Add the mesh as a triangle
        CustomTriangleMesh tm = new CustomTriangleMesh(mesh);
        tm.setColor(new Color3f(0, 0, 1));
        Content c = univ.addCustomMesh(tm, "triangle");

// Add the mesh as a triangle
        mesh.add(new Point3f(mesh.get(0))); // to close the path
        CustomLineMesh lm = new CustomLineMesh(
                mesh, CustomLineMesh.CONTINUOUS);
        lm.setColor(new Color3f(1, 0, 0));
        univ.addCustomMesh(lm, "lines");
        lm.setLineWidth(5);
        lm.setPattern(CustomLineMesh.DASH);

// after adding the CustomMesh to the universe, we have a
// reference to the Content, which can be used for further
// modification
        c.setTransparency(0.5f);

        //more complex meshes
        // Use Mesh_Maker to create more complex surfaces like spheres,
// tubes or discs:

// define a sphere with center at the origin and radius 50
        /*
        double x = 0, y = 0, z = 30, r = 50;
        int meridians = 24;
        int parallels = 24;
        mesh = Mesh_Maker.createSphere(x, y, z, r, meridians, parallels);
        Color3f color = null;
        univ.addTriangleMesh(mesh, color, "sphere");
        */



    }
}
