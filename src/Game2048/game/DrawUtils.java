package Game2048.game;

import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawUtils {

	public static int getMessageWidth(String message, Font font, Graphics2D g) {
		g.setFont(font);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
		return (int) bounds.getWidth();
	}

	public static int getMessageHeight(String message, Font font, Graphics2D g) {
		g.setFont(font);
		if(message.length() == 0) return 0;
		TextLayout tl = new TextLayout(message, font, g.getFontRenderContext());
		return (int) tl.getBounds().getHeight();
	}
	
	public static String formatTime(long millis) {
		String formattedTime = "";

		int hours = (int) (millis / 3600000);
		if (hours >= 1) {
			millis -= hours * 3600000;
			formattedTime += hours + ":";
		}

		int minutes = (int) (millis / 60000);
		if (minutes >= 1) {
			millis -= minutes * 60000;
			if (minutes < 10) {
				formattedTime += "0" + minutes + ":";
			}
			else {
				formattedTime += minutes + ":";
			}
		}

		int seconds = (int) (millis / 1000);
		if (seconds >= 1) {
			millis -= seconds * 1000;
			if (seconds < 10) {
				formattedTime += "0" + seconds + ":";
			}
			else {
				formattedTime += seconds + ":";
			}
		}

		if (millis > 99) {
			formattedTime += millis;
		}
		else if (millis > 9) {
			formattedTime += "0" + millis;
		}
		else {
			formattedTime += "00" + millis;
		}

		return formattedTime;
	}
}
