package easyFlyer.model.event;

import java.io.IOException;

public interface ImageUpdateListener {
	public void updateImage(ImageUpdateEvent e) throws IOException;
}
