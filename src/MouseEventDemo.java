import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MouseEventDemo extends Frame implements MouseListener, MouseMotionListener {

    // Variables
    private int windowWidth;
    private int windowHeight;

    private Graphics shapes;

    private int circleRadius;
    private int crossWidth;
    private int crossRatio;
    private int squareWidth;


    // Constructor
    MouseEventDemo(){

        // Listeners
        addMouseListener(this);
        addMouseMotionListener(this);

        // Frame
        this.windowWidth = 600;
        this.windowHeight = 600;
        setSize(this.windowWidth,this.windowHeight);
        setLayout(null);
        setVisible(true);

        // Graphics
        this.shapes = getGraphics();
        this.circleRadius = 20;
        this.crossWidth = 40;
        this.crossRatio = 5;
        this.squareWidth = 40;
    }

    // MouseListener Methods
    @Override
    public void mouseClicked(MouseEvent e) {
        // Basic message
        System.out.println("Clicked");

        // Draw Shape
        shapeSelect(e, this.shapes, Color.GREEN);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Basic message
        System.out.println("Pressed");

        // Draw Shape
        shapeSelect(e, this.shapes, Color.BLUE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Basic message
        System.out.println("Released");

        // Draw Shape
        shapeSelect(e, this.shapes, Color.RED);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Basic message
        System.out.println("Entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Basic message
        System.out.println("Exited");

        // Clear Shapes
        clearShapes(this.shapes);

    }

    // MouseMotionListener Methods
    @Override
    public void mouseDragged(MouseEvent e) {
        // Basic Message
        System.out.println("Dragged");

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Basic Message
        System.out.println("Moved");

    }

    // Graphic Methods
    private void shapeSelect(MouseEvent e, Graphics shape, Color c){
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                drawCircle(e.getX(), e.getY(), this.circleRadius,shape, c);
                //drawPolygon(e.getX(), e.getY(), this.circleRadius, 3, shape, c);
                break;
            case MouseEvent.BUTTON2:
                drawSquare(e.getX(), e.getY(), this.squareWidth, shape, c);
                break;
            case MouseEvent.BUTTON3:
                drawCross(e.getX(), e.getY(), this.crossWidth, this.crossRatio, shape, c);
                break;
        }
    }

    private void drawCircle(int x, int y, int r, Graphics shape, Color c){
        shape.setColor(c);
        shape.fillOval(x - r/2, y - r/2, r, r);
    }

    private void drawCross(int x, int y, int r, int f, Graphics shape, Color c){
        shape.setColor(c);
        shape.fillRect(x - r/(f*2), y - r/2, r/f, r);
        shape.fillRect(x - r/2, y - r/(f*2), r, r/f);
    }

    private void drawSquare(int x, int y, int r, Graphics shape, Color c){
        shape.setColor(c);
        shape.fillRect(x - r/2, y - r/2, r, r);
    }

    private void drawPolygon(int x, int y, int r, int n, Graphics shape, Color c){

        // Calculate Points
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            xList.add( (int) Math.round(x + r*Math.cos(2 * Math.PI * i/ n)));
            yList.add( (int) Math.round(y + r*Math.sin(2 * Math.PI * i/ n)));
        }

        // Create Polygon
        Polygon p = new Polygon( xList.stream().mapToInt(i -> i).toArray(), yList.stream().mapToInt(i -> i).toArray(), n);

        // Draw Polygon
        shape.setColor(c);
        shape.fillPolygon(p);
    }

    private void clearShapes( Graphics shape){
        shape.clearRect(0,0, this.windowWidth, this.windowHeight);
    }

}