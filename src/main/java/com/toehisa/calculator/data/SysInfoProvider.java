package com.toehisa.calculator.data;

import java.awt.*;

public class SysInfoProvider {

    public static class ScreenResolution {
        private static GraphicsDevice[] graphicsDevices;

        private static int screensCount;
        private static double[] screensWidth;
        private static double[] screensHeight;

        static {
            try {
                graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                screensCount = graphicsDevices.length;
                screensWidth = new double[screensCount];
                screensHeight = new double[screensCount];
                fillScreensResolutionInfo();
            } catch (HeadlessException e) {
                System.exit(0);
            }
        }

        public static double getScreenWidth(int monitorIndex) {
            if (monitorIndex < 0 || monitorIndex > screensCount - 1) {return 1920;}
            return screensWidth[monitorIndex];
        }

        public static double getScreenHeight(int monitorIndex) {
            if (monitorIndex < 0 || monitorIndex > screensCount - 1) {return 1080;}
            return screensHeight[monitorIndex];
        }

        public static int getScreensCount() {
            return screensCount;
        }

        private static void fillScreensResolutionInfo() {
            for (int i = 0; i < screensCount; i++) {
                DisplayMode mode = graphicsDevices[i].getDisplayMode();
                screensWidth[i] = mode.getWidth();
                screensHeight[i] = mode.getHeight();
            }
        }
    }

}
