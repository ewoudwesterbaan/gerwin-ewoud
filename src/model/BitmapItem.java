package model;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items hebben de verantwoordelijkheid om zichzelf te tekenen.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem implements Item {
  private BufferedImage bufferedImage;
  private String imageName;
  
  protected static final String FILE = "Bestand ";
  protected static final String NOTFOUND = " niet gevonden";

// level staat voor het item-level; name voor de naam van het bestand met de afbeelding
	public BitmapItem(String name) {
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

// geef de bestandsnaam van de afbeelding
	public String getName() {
		return imageName;
	}

// geef de bounding box van de afbeelding
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		return new Rectangle((int) (style.indent * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (style.leading * scale)) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}

// teken de afbeelding
	public void draw(int x, int y, float scale, Graphics g, ImageObserver observer, Style style) {
		int width = x + (int) (style.indent * scale);
		int height = y + (int) (style.leading * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
                (int) (bufferedImage.getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "BitmapItem[" + imageName + "]";
	}
}
