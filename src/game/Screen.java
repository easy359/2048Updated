package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Resources;
import util.Static;
import util.UserInput;

public class Screen extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;

    private int width, height;

    private Resources res;

    public Screen(String title, int width, int height) {
	setFocusable(true);
	requestFocus();
	setPreferredSize(new Dimension(width, height));

	this.width = width;
	this.height = height;

	UserInput input = new UserInput();

	addKeyListener(input);
	addMouseListener(input);
	addMouseMotionListener(input);
	addMouseWheelListener(input);

	frame = new JFrame(title);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(this);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	frame.addMouseListener(input);
	frame.addMouseMotionListener(input);

	res = new Resources();
	res.loadResources();
    }

    public void update() {
	UserInput.update();
    }

    public void render(int[][] board) {
	for (int y = 0; y < board[0].length; y++) {
	    for (int x = 0; x < board.length; x++) {
		addPixels(res.getImage(board[x][y]).getPixels(), (x * 150) + 16,
			(y * 150) + 16, 128, 128);
	    }
	}
	repaint();
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(Color.black);
	g.fillRect(0, 0, width, height);
	g.drawImage(image, 0, 0, width, height, null);
	Arrays.fill(pixels, Color.gray.getRGB());
	g.dispose();
    }

    private int[] addPixels(int[] add, int addX, int addY, int addWidth,
	    int addHeight) {
	return Static.addPixels(pixels, width, height, add, addX, addY,
		addWidth, addHeight);
    }
}
