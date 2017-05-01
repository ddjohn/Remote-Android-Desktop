package com.ddjohn.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.MultiLineReceiver;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;

public class DajoADB {
	private final static Logger log = Logger.getLogger(DajoADB.class.getName());

	private AndroidDebugBridge adb = null;
	private static DajoADB instance = null;
	private IDevice device = null;
	private static boolean landscape = false;
	
	public static void init() {
		AndroidDebugBridge.init(false);		
	}

	public static void terminate() {
		AndroidDebugBridge.terminate();		
	}
	
	public static DajoADB getInstance() {
		if(instance == null) 
			instance = new DajoADB();
		
		return instance;
	}

	private DajoADB() {
		DajoADB.init();

		adb = AndroidDebugBridge.createBridge("adb", true);	
		
		while(adb.hasInitialDeviceList() == false) {
			try {
				Thread.sleep(100);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		IDevice[] devices = adb.getDevices();
		for(IDevice device : devices) {
			log.info("device=" + device);
		}

		device = devices[0];

	}

	public RawImage getScreenshot() {
		log.info("Snap");
		try {
			if(landscape)
				return device.getScreenshot().getRotated();
			else
				return device.getScreenshot();
		} 
		catch(TimeoutException|AdbCommandRejectedException|IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage getImage() {
		RawImage fb = this.getScreenshot();
				
		BufferedImage image = new BufferedImage(fb.width, fb.height, BufferedImage.TYPE_INT_ARGB);
		int index = 0;
		int IndexInc = fb.bpp >> 3;
		for (int y = 0 ; y < fb.height ; y++) {
			for (int x = 0 ; x < fb.width ; x++) {
				int value = fb.getARGB(index);
				index += IndexInc;
				image.setRGB(x, y, value);
			}
		}

		return image;
	}
	
	public boolean isConnected() {
		return adb.isConnected();
	}

	public void executeShellCommand(String string, MultiLineReceiver multiLineReceiver) throws TimeoutException, AdbCommandRejectedException, ShellCommandUnresponsiveException, IOException {
		device.executeShellCommand(string, multiLineReceiver);
	}

	public static void setLandscape(boolean value) {
		landscape = value;
	}

	public static boolean getLandscape() {
		return landscape;
	}
}

/*
System.out.println(device.getAvdName());
System.out.println(device.getName());
System.out.println(device.getPropertyCount());
System.out.println(device.getSerialNumber());
System.out.println(device.hasClients());
//System.out.println(device.getBatteryLevel());
System.out.println(device.getFileListingService());
System.out.println(device.getProperties());
System.out.println(device.getState());
 */


//System.out.println(device.getSyncService());

