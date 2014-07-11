package ben.kn.toolbox.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;

import javax.imageio.ImageIO;

public class ImageUtility {

	public static void main(String[] args) {
		ImageUtility.scaleImagesInDirectory(
				"/Users/Ben/Development/workspace/Preview/data/wallpapers",
				"/Users/Ben/Development/workspace/Preview/data/temp", .5f, null);
		ImageUtility.resizeImagesInDirectory(
				"/Users/Ben/Development/workspace/Preview/data/wallpapers",
				"/Users/Ben/Development/workspace/Preview/data/temp", 300, 450, "_th");

		System.out.println(ImageUtility.getNameWithoutExtension(new File(
				"/Users/Ben/Development/workspace/Preview/data/wallpapers/1.jpg")));
	}

	public static void scaleImage(File file, String destDir, float scale, String suffix) {
		try {
			final File destdir = new File(destDir);
			final String extension = ".jpg";

			final BufferedImage image = ImageIO.read(file);
			final int w = (int) ((float) image.getWidth() * scale);
			final int h = (int) ((float) image.getHeight() * scale);
			final Image scaledimg = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

			final BufferedImage scaledbi = new BufferedImage(scaledimg.getWidth(null),
					scaledimg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			scaledbi.getGraphics().drawImage(scaledimg, 0, 0, null);

			String newFileName;
			if ( suffix == null ) {
				newFileName = getNameWithoutExtension(file) + "_" + w + "x" + h + extension;
			} else {
				newFileName = getNameWithoutExtension(file) + suffix + extension;
			}

			final File destfile = new File(destdir, newFileName);
			ImageIO.write(scaledbi, "jpeg", destfile);

			System.out.println("Scaled: " + file + " --> " + destfile);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void scaleImagesInDirectory(String imageSrcDir, String destDir, float scale,
			String suffix) {
		try {
			final File srcdir = new File(imageSrcDir);
			final File destdir = new File(destDir);
			final String extension = ".jpg";

			final File[] srcfiles = srcdir.listFiles(new FileFilter() {
				public boolean accept(File f) {
					return (f.isFile() && f.getName().toLowerCase()
							.endsWith(extension.toLowerCase()));
				}
			});

			for ( File file : srcfiles ) {
				final BufferedImage image = ImageIO.read(file);
				final int w = (int) ((float) image.getWidth() * scale);
				final int h = (int) ((float) image.getHeight() * scale);
				final Image scaledimg = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

				final BufferedImage scaledbi = new BufferedImage(scaledimg.getWidth(null),
						scaledimg.getHeight(null), BufferedImage.TYPE_INT_RGB);
				scaledbi.getGraphics().drawImage(scaledimg, 0, 0, null);

				String newFileName;
				if ( suffix == null ) {
					newFileName = getNameWithoutExtension(file) + "_" + w + "x" + h + extension;
				} else {
					newFileName = getNameWithoutExtension(file) + suffix + extension;
				}

				final File destfile = new File(destdir, newFileName);
				ImageIO.write(scaledbi, "jpeg", destfile);

				System.out.println("Scaled: " + file + " --> " + destfile);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void resizeImagesInDirectory(String imageSrcDir, String destDir, int height,
			int width, String suffix) {
		try {
			final File srcdir = new File(imageSrcDir);
			final File destdir = new File(destDir);
			final String extension = ".jpg";

			final File[] srcfiles = srcdir.listFiles(new FileFilter() {
				public boolean accept(File f) {
					return (f.isFile() && f.getName().toLowerCase()
							.endsWith(extension.toLowerCase()));
				}
			});

			for ( File file : srcfiles ) {
				final BufferedImage image = ImageIO.read(file);
				final Image scaledimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

				final BufferedImage scaledbi = new BufferedImage(scaledimg.getWidth(null),
						scaledimg.getHeight(null), BufferedImage.TYPE_INT_RGB);
				scaledbi.getGraphics().drawImage(scaledimg, 0, 0, null);

				String newFileName;
				if ( suffix == null ) {
					newFileName = getNameWithoutExtension(file) + "_" + width + "x" + height
							+ extension;
				} else {
					newFileName = getNameWithoutExtension(file) + suffix + extension;
				}

				final File destfile = new File(destdir, newFileName);
				ImageIO.write(scaledbi, "jpeg", destfile);

				System.out.println("Scaled: " + file + " --> " + destfile);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String getNameWithoutExtension(File file) {
		String name = file.getName();
		int cutIndex = name.lastIndexOf(".");
		name = name.substring(0, cutIndex);
		return name;
	}
}
